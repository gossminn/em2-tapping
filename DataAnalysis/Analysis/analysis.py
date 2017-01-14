from scipy import stats
from tapping_data import TappingData

# Analyzer object
analyzer = TappingData("../Data")

# Write sessions to excel
analyzer.write_sessions_xlsx()

# Write condition rrts to excel
analyzer.write_session_rrts()

# Print descriptive statistics
print("\nRTs: {}\nErrors: {}\nDelta RTs: {}".format(
        analyzer.rt_per_condition,
        analyzer.errs_per_condition,
        analyzer.drts_per_condition
    ))

print("\nRT lists per condition: {}".format(analyzer.rts_per_condition))

# Print statistical tests (ANOVA)
rts = analyzer.rts_per_condition
print("\nANOVA: {}".format(stats.f_oneway(rts['easy'], rts['lsys'], rts['golden'])))


# Print statistical tests (ANOVA + TTEST) per participant
for s in analyzer.sessions:
    rts = s.get_condition_rtlist()
    print("\nParticipant: {}".format(s.subj_id))
    print("ANOVA: {}".format(stats.f_oneway(rts['easy'], rts['lsys'], rts['golden'])))
    print("TTEST: {}".format(stats.ttest_ind(rts['golden'], rts['lsys'])))
