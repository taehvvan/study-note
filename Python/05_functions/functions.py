# functions.py

def greet(name="Guest"):
    return f"Hello, {name}!"

print(greet("Alice"))
print(greet())

# 람다 함수
add = lambda x, y: x + y
print("Lambda add:", add(2, 3))
