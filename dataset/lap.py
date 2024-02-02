class Lap:
    def __init__(self):
        self._running = False
        self._start = 0
        self._fractions = []

    def start(self):
        self._running = True
        self._start = time.perf_counter()

    def stop(self):
        self._fractions.append(time.perf_counter() - self._start)
        self._start = 0
        self._running = False
