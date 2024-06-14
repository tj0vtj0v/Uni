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
paths = ["/question/{intend}", "/generals/intends", "/pos_neg", "/generals"]


@app.get("/")
async def root():
    return paths


@app.get("/question/{intend}")
async def get_question(intend: str):
    return question_data[intend]


@app.get("/generals/intends")
async def get_intends():
    return question_data.keys()


@app.get("/pos_neg")
async def get_pos_neg():
    return answer_data


@app.get("/generals")
async def get_generals():
    return general_data
