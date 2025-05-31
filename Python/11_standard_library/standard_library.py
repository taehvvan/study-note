# standard_library.py

from datetime import datetime
import os

now = datetime.now()
print("현재 시간:", now.strftime("%Y-%m-%d %H:%M:%S"))

print("현재 디렉토리:", os.getcwd())
