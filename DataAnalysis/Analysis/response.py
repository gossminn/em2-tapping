class Response:
    def __init__(self, resp_id, char_time, tap_time):
        # Set counter
        self.resp_id = resp_id

        # Time information
        self.char_time = int(char_time)
        self.tap_time = int(tap_time)
        self.delta_time = self.tap_time - self.char_time
        if self.delta_time < 0:  # Disallow negative reaction times
            self.delta_time = 0
        self.error = True if self.delta_time == 0 else False
