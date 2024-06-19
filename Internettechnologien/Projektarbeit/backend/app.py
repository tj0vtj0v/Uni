from datetime import datetime
import json
import random

from fastapi import FastAPI  # provides API application
from starlette.middleware.cors import CORSMiddleware

# assign API Object
app = FastAPI(
    title="IT-Project Knowledge-DB API",
    version="0.1.0"
)
app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:4200"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

question_data: dict = json.loads(open('questions.json').read())
general_data: dict = json.loads(open('generals.json').read())
answer_data: dict = json.loads(open('answers.json').read())


def _sid_generator():
    sid = 0
    while True:
        sid += 1
        yield sid

_sid_iterator = _sid_generator()


@app.get("/")
async def root():
    return "Hello, I am your assistance at finding your sort of drone according to your application."


@app.get("/sid")
async def create_sid():
    return next(_sid_iterator)


@app.get("/time")
async def get_time():
    return datetime.strftime(datetime.now(), "%H:%M")


@app.get("/{sid}/{text}")
async def compute_text(sid: int, text: str):
    return f"session {sid} received: {text}"


def get_result(topic: str, answer: str):
    question = question_data[topic]
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
