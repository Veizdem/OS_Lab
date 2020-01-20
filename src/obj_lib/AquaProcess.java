package obj_lib;

public class AquaProcess {
    // служебные
    private byte id;
    private byte commandCounter;
    private byte type;
    private byte x;
    private byte y;
    private byte direction;
    private byte reserve1;
    private byte reserve2;
    private String[] name;
    private byte priority;

    // системные
    private byte hp;
    private byte visibleObject;
    private byte dist;
    private byte errCode;
    private byte reserve3;
    private byte reserve4;

    // данные
    private byte data1;
    private byte data2;
    private byte data3;
    private byte data4;

    // код процесса
    private String[] code;

    public AquaProcess() {

    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public byte getCommandCounter() {
        return commandCounter;
    }

    public void setCommandCounter(byte commandCounter) {
        this.commandCounter = commandCounter;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getX() {
        return x;
    }

    public void setX(byte x) {
        this.x = x;
    }

    public byte getY() {
        return y;
    }

    public void setY(byte y) {
        this.y = y;
    }

    public byte getDirection() {
        return direction;
    }

    public void setDirection(byte direction) {
        this.direction = direction;
    }

    public byte getReserve1() {
        return reserve1;
    }

    public void setReserve1(byte reserve1) {
        this.reserve1 = reserve1;
    }

    public byte getReserve2() {
        return reserve2;
    }

    public void setReserve2(byte reserve2) {
        this.reserve2 = reserve2;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public byte getHp() {
        return hp;
    }

    public void setHp(byte hp) {
        this.hp = hp;
    }

    public byte getVisibleObject() {
        return visibleObject;
    }

    public void setVisibleObject(byte visibleObject) {
        this.visibleObject = visibleObject;
    }

    public byte getDist() {
        return dist;
    }

    public void setDist(byte dist) {
        this.dist = dist;
    }

    public byte getErrCode() {
        return errCode;
    }

    public void setErrCode(byte errCode) {
        this.errCode = errCode;
    }

    public byte getReserve3() {
        return reserve3;
    }

    public void setReserve3(byte reserve3) {
        this.reserve3 = reserve3;
    }

    public byte getReserve4() {
        return reserve4;
    }

    public void setReserve4(byte reserve4) {
        this.reserve4 = reserve4;
    }

    public String[] getCode() {
        return code;
    }

    public void setCode(String[] code) {
        this.code = code;
    }

    // сделать щаг в выбранном направлении
    public void makeStep() {

    }

    // повернуть налево
    public void goLeft() {

    }

    // повернуть направо
    public void goRight() {

    }

    // сравнить два операнда
    public void compare(byte o1, byte o2) {

    }

    // если результат сравнения о1 больше о2


    // выполнить код (читаем текущую строку инструкции, парсим ее, выполняем метод, ставим к исполнению следующую)
    public void runCode() {

    }
}
