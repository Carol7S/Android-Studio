package df.cn.edu.zafu;

public class FundInfo {
    private int id;
    private String name; // 基金名称
    private String value;// 净值
    private String size;// 规模
    private String time;// 周期
    private String DailyIncrease;//日增长
    private String WeeklyIncrease;
    private String MonthlyIncrease;
    private String YearlyIncrease;
    private int holdNum;//持有量
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }
    public String getvalue() {
        return value;
    }
    public void setvalue(String value) {
        this.value = value;
    }
    public String getsize() {
        return size;
    }
    public void setsize(String size) {
        this.size = size;
    }
    public String gettime() {
        return time;
    }
    public void settime(String time) {
        this.time = time;
    }
    public String getDailyIncrease() {
        return DailyIncrease;
    }
    public void setDailyIncrease(String DailyIncrease) {
        this.DailyIncrease = DailyIncrease;
    }
    public String getWeeklyIncrease() {
        return WeeklyIncrease;
    }
    public void setWeeklyIncrease(String WeeklyIncrease) {
        this.WeeklyIncrease = WeeklyIncrease;
    }
    public String getMonthlyIncrease() {
        return MonthlyIncrease;
    }
    public void setMonthlyIncrease(String MonthlyIncrease) {
        this.MonthlyIncrease = MonthlyIncrease;
    }
    public String getYearlyIncrease() {
        return YearlyIncrease;
    }
    public void setYearlyIncrease(String YearlyIncrease) {
        this.YearlyIncrease = YearlyIncrease;
    }
    public int getholdNum() {
        return holdNum;
    }
    public void setholdNum(int holdNum) {
        this.holdNum = holdNum;
    }


    public FundInfo(String name, String value, String size, String time, String DailyIncrease, String WeeklyIncrease, String MonthlyIncrease, String YearlyIncrease, int holdNum) {
        super();
        this.name = name;
        this.value = value;
        this.size = size;
        this.time = time;
        this.DailyIncrease = DailyIncrease;
        this.WeeklyIncrease = WeeklyIncrease;
        this.MonthlyIncrease = MonthlyIncrease;
        this.YearlyIncrease = YearlyIncrease;
        this.holdNum = holdNum;
    }

    public FundInfo(int id, String name, String value, String size, String time, String DailyIncrease, String WeeklyIncrease, String MonthlyIncrease, String YearlyIncrease, int holdNum) {
        super();
        this.id = id;
        this.name = name;
        this.value = value;
        this.size = size;
        this.time = time;
        this.DailyIncrease = DailyIncrease;
        this.WeeklyIncrease = WeeklyIncrease;
        this.MonthlyIncrease = MonthlyIncrease;
        this.YearlyIncrease = YearlyIncrease;
        this.holdNum = holdNum;
    }
    @Override
    public String toString() {
        return "FundInfo [id="+id+  ", name="+name+  ", value="+value+  ", size="+size+  ", time="+time+  ", DailyIncrease="+DailyIncrease+  ", WeeklyIncrease="+WeeklyIncrease+  ", MonthlyIncrease="+MonthlyIncrease+  ", YearlyIncrease="+YearlyIncrease+  ", holdNum="+holdNum+  "]";
    }
}
