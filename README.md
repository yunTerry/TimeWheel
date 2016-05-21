## TimeWheel
####TimeWheel Dialog to set the time period

####Get the time period you set
```java
timebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimeWheel(MainActivity.this, new TimeListener() {
                    @Override
                    public void getTime(int[] time) {
                        //time[0]  start hour
                        //time[1]  start minute
                        //time[2]  endhour
                        //time[3]  end minute
                        timetv.setText(getTimeStr(time));
                    }
                });
            }
        });
```
##Thanks
####https://github.com/maarek/android-wheel