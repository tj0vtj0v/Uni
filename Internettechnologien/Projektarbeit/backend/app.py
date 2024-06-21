from datetime import datetime

from fastapi import FastAPI
from starlette.middleware.cors import CORSMiddleware

from BotSession import BotSession


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
sessions: dict[int, BotSession] = {}


@app.get("/")
async def root():
    return "Hello, I am your assistance at finding your sort of drone according to your application. Let's get started!"


@app.get("/sid")
async def create_sid():
    session = BotSession()
    sessions[session.sid] = session
    return session.sid


@app.get("/time")
async def get_time():
    return datetime.strftime(datetime.now(), "%H:%M")


@app.get("/{sid}/{text}")
async def compute_text(sid: int, text: str):
    return sessions[sid].generate_answer(text)


