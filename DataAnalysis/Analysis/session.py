import statistics

from trial import Trial


class Session:
    def __init__(self, subj_id, session_type, csv_contents):
        # General information
        self.subj_id = subj_id
        self.session_type = session_type

        # CSV contents
        self.csv_contents = csv_contents

        # Get trials and make a list of responses
        self.trials = []
        for (trial_header, trial_rows) in self.get_trial_rows():
            self.trials.append(Trial(subj_id, trial_header, trial_rows))

        # Statistics
        # Average RT per condition
        self.rt_per_condition = self.get_condition_rts()

        # Average error rate per condition
        self.errors_per_condition = self.get_condition_errs()

        # Average RT change per condition
        self.delta_rt_per_condition = self.get_condition_drts()

        # Mean RT per resp_id per condition
        self.resp_rts_per_condition = self.get_condition_rrts()

    def get_trial_rows(self):
        # Get trial headers with their respective row numbers
        trial_starts = []
        for row_id, row in enumerate(self.csv_contents):
            if row[1] != "":
                trial_starts.append((row_id, row))

        # Get tuples of trial headers and their respective trials contents
        trials = []
        for index, (row_id, row) in enumerate(trial_starts):
            try:
                next_trial_row = trial_starts[index + 1][0]
            except IndexError:
                next_trial_row = len(self.csv_contents) - 1
            finally:
                trials.append((row, self.csv_contents[row_id + 1: next_trial_row]))
        return trials

    def get_condition_rts(self):
        # Empty dictionary
        rts = self.empty_condition_dict()

        # Find average for each condition
        for condition in rts:
            averages = [t.mean_rt for t in self.trials if t.condition == condition]
            rts[condition] = statistics.mean(averages)

        return rts

    def get_condition_rtlist(self):
        # Empty dictionary
        rts = self.empty_condition_dict()

        # Find list of mean RTs per trial for each condition
        for condition in rts:
            averages = [t.mean_rt for t in self.trials if t.condition == condition]
            rts[condition] = averages

        return rts

    def get_condition_errs(self):
        # Dictionary with condition entries
        errs = self.empty_condition_dict()

        # Find average for each condition
        for condition in errs:
            err_rates = [t.error_rate for t in self.trials if t.condition == condition]
            errs[condition] = statistics.mean(err_rates)

        return errs

    def get_condition_drts(self):
        # Empty dictionary with condition entries
        deltas = self.empty_condition_dict()

        # Find average delta_rt for each condition
        for condition in deltas:
            delta_rts = [t.mean_delta_rt for t in self.trials if t.condition == condition]
            deltas[condition] = statistics.mean(delta_rts)

        return deltas

    def get_condition_rrts(self):
        # Empty dictionary with condition entries
        rrts = self.empty_condition_dict(list_entry=True)

        # Make list of average RTs per response for each condition
        for condition in rrts:
            # Count up to 35 responses
            for resp_id in range(35):
                # List of RTs for this resp_id
                rts = []

                # Loop through trials
                for t in self.trials:
                    # If wrong condition: continue
                    if not t.condition == condition:
                        continue

                    # If out of range: continue
                    if not resp_id < len(t.responses):
                        continue

                    # Add RT for this resp_id to rts list
                    rt = t.responses[resp_id].delta_time
                    if rt != 0:  # Ignore errors
                        rts.append(rt)

                # Add mean RT to condition rrts list
                try:
                    rrts[condition].append(statistics.mean(rts))
                except statistics.StatisticsError:
                    rrts[condition].append(0)

        return rrts

    def empty_condition_dict(self, list_entry=False):
        # Empty dictionary
        d = {}
        # Create empty entries for each condition
        for trial in self.trials:
            d[trial.condition] = 0 if not list_entry else []

        return d

