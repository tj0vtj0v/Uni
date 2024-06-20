import json
from pprint import pprint


def _sid_generator():
    sid = 0
    while True:
        sid += 1
        yield sid


_sid_iterator = _sid_generator()


class BotSession:
    sid: int

    def __init__(self):
        self.sid = next(_sid_iterator)

        self.question_data: dict = json.loads(open('questions.json').read())
        self.general_data: dict = json.loads(open('generals.json').read())
        self.answer_data: dict = json.loads(open('answers.json').read())

    def test(self):
        return [self.question_data[key]["result"] for key in self.question_data.keys()]

    def generate_answer(self, text: str):
        return f"session {self.sid} received message: {text}"

    def check_for_general(self):
        pass

    def get_result(self, topic: str, answer: str):
        question = self.question_data[topic]
        results = []
        max_count = 0

        if not question["result"]:
            result = ""
            for char in answer:
                if char.isdigit() or char == "." or char == ",":
                    result += char
            result = result.split(",")[0].split(".")[0]
            if result == "":
                return -1
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

        spot_count = 0
        for word in self.answer_data["negative"]:
            if word in answer:
                spot_count += 1

        if spot_count % 2 == 0 or results[0] == "none" or results[0] == "no":  # not negated -> positive
            if len(results) == 1:
                return results[0]
            else:
                return -1
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
                return -1


c = BotSession()
pprint(c.test())
