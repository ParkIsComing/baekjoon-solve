# 결과를 출력합니다.
def print_result(cube):
    for i in range(3):
        for j in range(3):
            print(cube[2][i][j], end="")
        print()


# 파라미터로 받은 면의 큐브를 시계 방향으로 90도 회전시킵니다.
def move_dimension(cube, index):
    for _ in range(2):
        temp = cube[index][0][0]
        cube[index][0][0] = cube[index][1][0]
        cube[index][1][0] = cube[index][2][0]
        cube[index][2][0] = cube[index][2][1]
        cube[index][2][1] = cube[index][2][2]
        cube[index][2][2] = cube[index][1][2]
        cube[index][1][2] = cube[index][0][2]
        cube[index][0][2] = cube[index][0][1]
        cube[index][0][1] = temp


# 지시에 따라 큐브를 돌립니다.
def move(cube, direction):
    # 'U' 방향으로 돌리는 경우를 처리합니다.
    if direction == 'U':
        temp = cube[0][0]
        cube[0][0] = cube[4][0]
        cube[4][0] = cube[5][0]
        cube[5][0] = cube[3][0]
        cube[3][0] = temp
        move_dimension(cube, 2)

    # 'D' 방향으로 돌리는 경우를 처리합니다.
    elif direction == 'D':
        temp = cube[0][2]
        cube[0][2] = cube[3][2]
        cube[3][2] = cube[5][2]
        cube[5][2] = cube[4][2]
        cube[4][2] = temp
        move_dimension(cube, 1)

    # 'F' 방향으로 돌리는 경우를 처리합니다.
    elif direction == 'F':
        temp = cube[2][2]
        cube[2][2] = [cube[3][2][2], cube[3][1][2], cube[3][0][2]]
        cube[3][0][2], cube[3][1][2], cube[3][2][2] = cube[1][0]
        cube[1][0] = [cube[4][2][0], cube[4][1][0], cube[4][0][0]]
        cube[4][0][0], cube[4][1][0], cube[4][2][0] = temp
        move_dimension(cube, 0)

    # 'B' 방향으로 돌리는 경우를 처리합니다.
    elif direction == 'B':
        temp = cube[2][0]
        cube[2][0] = [cube[4][0][2], cube[4][1][2], cube[4][2][2]]
        cube[4][2][2], cube[4][1][2], cube[4][0][2] = cube[1][2]
        cube[1][2] = [cube[3][0][0], cube[3][1][0], cube[3][2][0]]
        cube[3][2][0], cube[3][1][0], cube[3][0][0] = temp
        move_dimension(cube, 5)

        # 'L' 방향으로 돌리는 경우를 처리합니다.
    elif direction == 'L':
        temp = [cube[0][0][0], cube[0][1][0], cube[0][2][0]]
        cube[0][0][0], cube[0][1][0], cube[0][2][0] = cube[2][0][0], cube[2][1][0], cube[2][2][0]
        cube[2][0][0], cube[2][1][0], cube[2][2][0] = cube[5][2][2], cube[5][1][2], cube[5][0][2]
        cube[5][0][2], cube[5][1][2], cube[5][2][2] = cube[1][2][0], cube[1][1][0], cube[1][0][0]
        cube[1][0][0], cube[1][1][0], cube[1][2][0] = temp
        move_dimension(cube, 3)

    # 'R' 방향으로 돌리는 경우를 처리합니다.
    elif direction == 'R':
        temp = [cube[0][0][2], cube[0][1][2], cube[0][2][2]]
        cube[0][0][2], cube[0][1][2], cube[0][2][2] = cube[1][0][2], cube[1][1][2], cube[1][2][2]
        cube[1][0][2], cube[1][1][2], cube[1][2][2] = cube[5][2][0], cube[5][1][0], cube[5][0][0]
        cube[5][0][0], cube[5][1][0], cube[5][2][0] = cube[2][2][2], cube[2][1][2], cube[2][0][2]
        cube[2][0][2], cube[2][1][2], cube[2][2][2] = temp
        move_dimension(cube, 4)

    # 큐브와 지시 명령을 파라미터로 받아 큐브 회전을 실행합니다.


def go(cube, comm):
    direction, count = comm
    # +는 시계방향으로 1번 회전, -는 시계 방향으로 3번 회전하는 것과 같습니다.
    count = 1 if count == '+' else 3
    for _ in range(count):
        move(cube, direction)


# 각 테스트 케이스에 대해서 큐브를 돌립니다.
for _ in range(int(input())):
    # 첫번째 입력 라인은 읽고 무시합니다.
    input()
    # 큐브를 돌린 방법을 입력 받습니다.
    comm = list(map(str, input().split()))

    # 큐브의 6개의 면을 나타내는 cube 배열을 초기화합니다.
    # 0은 큐브의 앞면, 1은 밑면, 2는 윗면, 3은 왼쪽면, 4는 오른쪽면, 5는 뒷면입니다.
    cube = [[] for _ in range(6)]

    # 각 면은 9개의 사각형으로 이루어져있습니다.
    for _ in range(3):
        cube[0].append(['r', 'r', 'r'])
        cube[1].append(['y', 'y', 'y'])
        cube[2].append(['w', 'w', 'w'])
        cube[3].append(['g', 'g', 'g'])
        cube[4].append(['b', 'b', 'b'])
        cube[5].append(['o', 'o', 'o'])
    while comm:
        go(cube, comm.pop(0))

    # 결과값(윗면)을 출력합니다.
    print_result(cube)
