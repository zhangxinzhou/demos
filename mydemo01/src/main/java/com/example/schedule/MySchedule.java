package com.example.schedule;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务类,处理一些定时任务
 * @author ZXZ
 * @version 时间:2017-4-7
 */
@Component
public class MySchedule {

	private Logger log=LoggerFactory.getLogger(getClass());
	
	/**
	 * 每隔一分钟汇报当前的时间
	 * 
	 * <ul>
	 *   <li>简单用法示例:</li>
	 *   <li>@Scheduled(fixedRate = 5000) ：上一次开始执行时间点之后5秒再执行</li>
	 *   <li>@Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行</li>
	 *   <li>@Scheduled(initialDelay=1000, fixedRate=5000) ：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次</li>
	 * </ul>
	 * @param    无参数
	 * @return   无返回值   
	 */
	@Scheduled(fixedRate = 600000)
	public void reportCurrentTime(){
		log.info("每隔一分钟报时->当前时间 : [{}]",LocalDateTime.now());
	}
	
	/**
	 * 每天每个小时汇报当时时间
	 * <p>按顺序依次为(一个cron表达式有至少6个（也可能7个）有空格分隔的时间元素。):</p>
	 * <ol>
	 *   <li>秒（0~59）</li>
	 *   <li>分钟（0~59）</li>
	 *   <li>小时（0~23）</li>
	 *   <li>天（月）（0~31，但是你需要考虑你月的天数）</li>
	 *   <li>月（0~11）</li>
	 *   <li>天（星期）（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT）</li>
	 *   <li>年份（1970－2099）</li>
	 * </ol>
	 * <p>可用的符号:</p>
	 * <ol>
	 *   <li>Seconds ,- * / 四个字符，有效范围为0-59的整数</li>
	 *   <li>Minutes ,- * / 四个字符，有效范围为0-59的整数</li>
	 *   <li>Hours ,- * / 四个字符，有效范围为0-23的整数</li>
	 *   <li>DayofMonth ,- * / ? L W C八个字符，有效范围为0-31的整数</li>
	 *   <li>Month ,- * / 四个字符，有效范围为1-12的整数或JAN-DEc</li>
	 *   <li>DayofWeek ,- * / ? L C #四个字符，有效范围为1-7的整数或SUN-SAT两个范围。1表示星期天，2表示星期一， 依次类推</li>
	 *   <li>Year ,- * / 四个字符，有效范围为1970-2099年</li>
	 * </ol>
	 * <p>各字符代表的含义:</p>
	 * <table border="1">
	 *   <tr>
	 *     <td>序号</td>
	 *     <td>符号</td>
	 *     <td>说明</td>
	 *   </tr>
	 *   <tr>
	 *     <td>1</td>
	 *     <td>*</td>
	 *     <td>“*”字符代表所有可能的值.因此，“*”在子表达式（月）里表示每个月的含义，“*”在子表达式（天（星期））表示星期的每一天</td>
	 *   </tr>
	 *   <tr>
	 *     <td>2</td>
	 *     <td>?</td>
	 *     <td>字符仅被用于天（月）和天（星期）两个子表达式，表示不指定值.当2个子表达式其中之一被指定了值以后，为了避免冲突，需要将另一个子表达式的值设为“？”</td>
	 *   </tr>
	 *   <tr>
	 *     <td>3</td>
	 *     <td>/</td>
	 *     <td>“/”字符用来指定数值的增量.例如：在子表达式（分钟）里的“0/15”表示从第0分钟开始，每15分钟 在子表达式（分钟）里的“3/20”表示从第3分钟开始，每20分钟（它和“3，23，43”）的含义一样</td>
	 *   </tr>
	 *   <tr>
	 *     <td>4</td>
	 *     <td>-</td>
	 *     <td>-表示范围，例如在Minutes域使用5-20，表示从5分到20分钟每分钟触发一次</td>
	 *   </tr>
	 *   <tr>
	 *     <td>5</td>
	 *     <td>,</td>
	 *     <td>,表示列出枚举值值。例如：在Minutes域使用5,20，则意味着在5和20分每分钟触发一次</td>
	 *   </tr>
	 *   <tr>
	 *     <td>6</td>
	 *     <td>/</td>
	 *     <td>表示起始时间开始触发，然后每隔固定时间触发一次，例如在Minutes域使用5/20,则意味着5分钟触发一次，而25，45等分别触发一次.</td>
	 *   </tr>
	 *   <tr>
	 *     <td>7</td>
	 *     <td>其他</td>
	 *     <td>其他</td>
	 *   </tr>
	 * </table> 
	 * <p>一些示例:</p>  
	 * <ol>
	 *   <li>"0 0 12 * * ?"    每天中午十二点触发 </li>
	 *   <li>"0 15 10 ? * *"    每天早上10：15触发 </li>
	 *   <li>"0 15 10 * * ?"    每天早上10：15触发 </li>
	 *   <li>"0 15 10 * * ? *"    每天早上10：15触发 </li>
	 *   <li>"0 15 10 * * ? 2005"    2005年的每天早上10：15触发 </li>
	 *   <li>"0 * 14 * * ?"    每天从下午2点开始到2点59分每分钟一次触发 </li>
	 *   <li>"0 0/5 14 * * ?"    每天从下午2点开始到2：55分结束每5分钟一次触发 </li>
	 *   <li>"0 0/5 14,18 * * ?"    每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发 </li>
	 *   <li>"0 0-5 14 * * ?"    每天14:00至14:05每分钟一次触发 </li>
	 *   <li>"0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发 </li>
	 *   <li>"0 15 10 ? * MON-FRI"    每个周一、周二、周三、周四、周五的10：15触发 </li>
	 * </ol>
	 * @param  无参数
	 * @return 无返回值
	 */
	@Scheduled(cron="0 0 * * * ?")
	public void reportHour(){
		log.info("整点任务报时->当前时间 : [{}]",LocalDateTime.now());
	}
}
