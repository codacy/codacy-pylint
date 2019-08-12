##Patterns: E0116

while True:
    try:
        pass
    finally:
        ##Err: E0116
        continue

while True:
    try:
        pass
    finally:
        break

while True:
    try:
        pass
    except Exception:
        pass
    else:
        continue
   