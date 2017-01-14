import statistics

from response import Response


class Trial:
    def __init__(self, subj_id, trial_header, trial_rows):
        # General information
        self.subj_id = subj_id
        self.trial_id = trial_header[1]
        self.condition = trial_header[4]
        self.stimulus = trial_header[5]
        self.trial_rows = trial_rows

        # Get list of responses
        self.responses = self.get_responses()

        # Statistics
        # Overall average
        self.mean_rt = statistics.mean(
            [r.delta_time for r in self.responses if r.delta_time != 0]
        )

        # Error rate
        self.error_rate = len([r for r in self.responses if r.error]) / float(len(self.responses))

        # Change in rts
        self.delta_rts = self.get_delta_rt()
        self.mean_delta_rt = statistics.mean(self.delta_rts)

        # Average rt per resp_id
        self.rt_per_resp_id = [r.delta_time for r in self.responses]

    def get_responses(self):
        # Define iterator object
        rows = iter(self.trial_rows)

        # Queue for 'extra' b-rows
        queued_rows = []

        # Responses and counter
        responses = []
        resp_counter = 0

        # Helper function for getting next item
        def get_next_row():
            if queued_rows:
                return queued_rows.pop()
            else:
                try:
                    return next(rows)
                except StopIteration:
                    return []

        # Loop over rows
        while True:
            # Get next row and check if it is not empty
            current_row = get_next_row()
            if not current_row:
                break

            # Find 'b' charShow event
            if not current_row[6] == "b":
                continue
            charshow_row = current_row

            # Find screenTap event
            while True:
                # Get next row
                current_row = get_next_row()

                # No more rows: stop
                if not current_row:
                    screentap_row = []
                    break

                # Found another 'b': queue it and stop
                if current_row[6] == "b":
                    queued_rows.append(current_row)
                    screentap_row = []
                    break

                # Found a screenTap: save it and stop
                if current_row[3] == "screenTap":
                    screentap_row = current_row
                    break

            # Define response object
            resp_counter += 1
            screentap_time = screentap_row[2] if screentap_row else 0
            response = Response(resp_counter, charshow_row[2], screentap_time)
            responses.append(response)

        return responses

    def get_delta_rt(self):
        # Define output list
        rt_changes = []

        # Ignore errors (= response with rt == 0)
        responses = [r for r in self.responses if r.delta_time != 0]

        # Loop through the responses of the current trial
        for i, r in enumerate(responses):
            # Ignore the first item in the list
            if i == 0:
                continue

            # Calculate difference between RT of current response with RT of previous response
            current_rt = r.delta_time
            prev_rt = responses[i - 1].delta_time
            difference = current_rt - prev_rt
            rt_changes.append(difference)

        # Return average change in RTs
        return rt_changes
