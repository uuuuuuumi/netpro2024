class NoHolidayException extends Exception {

    public NoHolidayException(int theday) {
        super("この日は平日だよ！学校だねぇ。。。エラーメッセージです。");
    }
}
