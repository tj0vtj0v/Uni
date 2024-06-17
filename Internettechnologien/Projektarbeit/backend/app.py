from fastapi import FastAPI  # provides API application
import json

# assign API Object
app = FastAPI(
    title="IT-Project Knowledge-DB API",
    version="0.1.0"
)

question_data: dict = json.loads(open('questions.json').read())
general_data: dict = json.loads(open('generals.json').read())
answer_data: dict = json.loads(open('answers.json').read())
paths = ["/question/{intend}", "/questions/intends", "/generals/intends", "/pos_neg", "/generals"]


def _question_generator():
    for key in question_data.keys():
        yield str(key)


qg = _question_generator()


@app.get("/")
async def root():
    return paths


@app.get("/question/{intend}")
async def get_question(intend: str):
    return question_data[intend]


@app.get("/questions/intends")
async def get_question_intends():
    return list(question_data.keys())


@app.get("/generals/intends")
async def get_intends():
    return list(question_data.keys())


@app.get("/pos_neg")
async def get_pos_neg():
    return answer_data


@app.get("/generals")
async def get_generals():
    return general_data


@app.get("/next_question")
async def get_next_question():
    return next(qg)


@app.get("/result/{intend}/{answer}")
async def get_result(intend: str, answer: str):
    question = question_data[intend]
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
    for word in answer_data["negative"]:
        if word in answer:
            spot_count += 1
    print(spot_count)
    print(results)

    if spot_count % 2 == 0 or results[0] == "none" or results[0] == "no":  # not negated -> poitive
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
