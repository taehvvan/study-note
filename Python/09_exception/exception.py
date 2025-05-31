# exception.py

try:
    x = int(input("Enter a number: "))
    print("10 / x =", 10 / x)
except ValueError:
    print("숫자를 입력하세요.")
except ZeroDivisionError:
    print("0으로 나눌 수 없습니다.")
finally:
    print("프로그램 종료")
