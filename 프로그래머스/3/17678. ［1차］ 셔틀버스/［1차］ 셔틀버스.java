import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        // 시간을 분으로 변환 후 정렬
        List<Integer> times = new ArrayList<>();
        for (String time : timetable) {
            times.add(toMinutes(time));
        }
        Collections.sort(times); 

        int shuttleTime = toMinutes("09:00"); // 첫 셔틀 시간
        int lastShuttleTime = shuttleTime + (n - 1) * t; // 마지막 셔틀 시간
        int index = 0; 

        // 셔틀별 탑승 시뮬레이션
        for (int i = 0; i < n; i++) { // 셔틀운행횟수 n은 최대 10
            int currentShuttleTime = shuttleTime + i * t; 
            int curCount = 0; //  탑승한 크루 수

            // 태울 수 있는 승객 확인
            while (index < times.size() && times.get(index) <= currentShuttleTime && curCount < m) {
                curCount++;
                index++;
            }

            // 마지막 셔틀의 상태를 저장
            if (i == n - 1) {
                // 셔틀에 자리가 남으면 셔틀 도착 시간에 탑승
                if (curCount < m) {
                    return toOutputFormat(lastShuttleTime);
                }
                // 콘은 같은 시각에 도착한 크루 중 대기열에서 제일 뒤에 선다.
                // 자리가 없으면 마지막 승객보다 1분 빨리 도착
                else {
                    return toOutputFormat(times.get(index - 1) - 1);
                }
            }
        }

        return "";
    }

    // 시간을 "HH:MM" -> 분으로 변환
    private int toMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    // 분 -> "HH:MM"로 변환
    private String toOutputFormat(int minutes) {
        int hours = minutes / 60;
        int mins = minutes % 60;
        return String.format("%02d:%02d", hours, mins);
    }
}
