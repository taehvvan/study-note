# file_io.py

# 쓰기
with open("sample.txt", "w") as f:
    f.write("Hello File!")

# 읽기
with open("sample.txt", "r") as f:
    content = f.read()
    print("File content:", content)
