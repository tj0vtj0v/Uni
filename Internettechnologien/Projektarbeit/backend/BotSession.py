import json


def _sid_generator():
    sid = 0
    while True:
        sid += 1
        yield sid


_sid_iterator = _sid_generator()


class BotSession:
    sid: int
    state: str
    question: str
    knowledge: dict

    def __init__(self):
        self.question_data: dict = json.loads(open('questions.json').read())
        self.general_data: dict = json.loads(open('generals.json').read())
        self.answer_data: dict = json.loads(open('answers.json').read())
        self.drones_data: dict = json.loads(open('drones.json').read())

        self.sid = next(_sid_iterator)
        self.state = "greet"
        self.question_iterator = self._question_generator()
        self.question = next(self.question_iterator)
        self.knowledge = {}

    def generate_answer(self, text: str):
        print(self.state)
        match self.state:
            case "greet":
                if self._check_for_general(text) == "capabilities":
                    self.state = "capabilities"
                    return self.general_data["capabilities"]["action"]

                self.state = "asking"
                return self.question_data[self.question]["question"]
            case "asking":
                general = self._check_for_general(text)

                if general:
                    self.state = general
                    return self.general_data[general]["action"]

                result = self._get_result(text)
                print(self.question, "-->", result)
                if not result:
                    return self.question_data[self.question]["fallback"]

                self.knowledge[self.question] = result
                try:
                    self.question = next(self.question_iterator)
                except StopIteration:
                    self.state = "evaluation"
                    return "Your recommendation is ready! Please answer anything to receive it!"
                return self.question_data[self.question]["question"]
            case "change":
                self.question_iterator = self._question_generator()
                for i in range([str(x) for x in self.question_data.keys()].index(self.question) - 2):
                    next(self.question_iterator)
                self.question = next(self.question_iterator)
            case "capabilities":
                if self._check_for_general(text) == self.state:
                    return self.general_data["capabilities"]["fallback"]
                self.state = "asking"
                return self.question_data[self.question]["fallback"]
            case "start", "exit":
                if self._confirmed(text):
                    self.state = "greet"
                    self.knowledge = {}
                    if self.state == "start":
                        return "Now you can start from the beginning!"
                    return "We evaluated the data you provided. Please answer anything to receive it!"
                self.state = "asking"
                return self.question_data[self.question]["fallback"]
            case "evaluation":
                return self._create_advice()

        return f"session {self.sid} received message: {text}"

    def _question_generator(self):
        for question in self.question_data.keys():
            yield question

    def _check_for_general(self, text: str) -> str:
        maximum = 0
        action = ""
        for general in [self.general_data[key] for key in self.general_data.keys()]:
            current = 0
            for keyword in general["keywords"]:
                if keyword in text:
                    current += 1
            if current > maximum:
                maximum = current
                action = general
        return action

    def _get_result(self, answer: str):
        question = self.question_data[self.question]
        results = []
        max_count = 0

        if not question["result"]:
            result = ""
            for char in answer:
                if char.isdigit() or char == "." or char == ",":
                    result += char
            result = result.split(",")[0].split(".")[0]
            if result == "":
                return False
            else:
                return result

        for word in list(question["result"].keys()):
            spot_count = 0
            for spot in question["result"][word]:
                if spot in answer:
                    spot_count += 1
            if spot_count > max_count:
                max_count = spot_count
                results = [word]
            elif spot_count == max_count:
                results.append(word)

        print(results, "is negated:", self._negated(answer))
        if not self._negated(answer) or results[0] == "none" or results[0] == "no":  # not negated -> positive
            if len(results) == 1:
                return results[0]
            else:
                return False
        else:
            new_results = list(question["result"].keys())
            for result in results:
                new_results.remove(result)
            if len(new_results) == 1:
                return new_results[0]
            elif "none" in new_results:
                return "none"
            elif "no" in new_results:
                return "no"
            else:
                return False

    def _negated(self, text: str) -> bool:
        spot_count = 0
        for word in self.answer_data["negative"]:
            if word in text:
                print("neg:", word)
                spot_count += text.count(word)
        return bool(spot_count % 2)

    def _confirmed(self, text: str) -> bool:
        confirm = False
        for keyword in self.answer_data["positive"]:
            confirm |= keyword in text
        return confirm and not self._negated(text)

    def _create_advice(self):
        return str(self.knowledge)
