# project_examples.py

# 계산기
def add(x, y):
    return x + y

print("3 + 5 =", add(3, 5))

# 숫자 맞추기 게임
import random

answer = random.randint(1, 10)
guess = int(input("Guess a number (1~10): "))
if guess == answer:
    print("Correct!")
else:
    print(f"Wrong! The answer was {answer}")
