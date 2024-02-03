class Stopwatch:
    def __init__(self, name: Optional[str] = None):
        self._name = name
        self.reset()

    def start(self):
        if self._lap is None:
            self._laps.append(Lap())
            self._lap = self._laps[-1]
            self._lap.start()
