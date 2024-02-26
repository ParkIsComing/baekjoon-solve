import java.util.*;

class Job {
    int request;
    int length;
    
    Job (int request, int length) {
        this.request= request;
        this.length = length;
    }
}

class Solution {
    public int solution(int[][] jobs) {
        int sum = 0;
        int answer = 0;
        List<Job> list = new ArrayList<>();
        for (int i=0; i<jobs.length; i++) {
            list.add(new Job(jobs[i][0], jobs[i][1]));
        }
        Collections.sort(list, (o1, o2) -> o1.request - o2.request);  
        
        PriorityQueue<Job> q = new PriorityQueue<>((e1,e2) -> e1.length - e2.length); // 수행시간 기준 우선순위 큐
        int count = 0;
        int qCount = 0;
        int time = 0;
        while (count < jobs.length) {
            // 우선 순위 큐에 현재 수행가능한 작업 수집
            for (int i=qCount; i<jobs.length; i++) {
                Job n = list.get(i);
                if (n.request <= time) { 
                    q.offer(n);
                    qCount++;
                } else {
                    break;
                }
            }
            
            if (q.size() > 0) { // 큐에 작업이 있는 경우
                Job cur = q.poll();
                time += cur.length;
                sum += time - cur.request;
                count++;
            } else {
                time++;
            }
        }
        System.out.println(sum);
                     
        answer = sum / jobs.length;
        return answer;
    }
}