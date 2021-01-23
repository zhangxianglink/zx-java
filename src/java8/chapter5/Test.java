package java8.chapter5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

	private static List<Transaction> transactions;

	static {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");
		transactions = Arrays.asList(new Transaction(brian, 2011, 300), new Transaction(raoul, 2012, 1000),
				new Transaction(raoul, 2011, 400), new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700),
				new Transaction(alan, 2012, 950));
	}

	/**
	 * (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。 (2) 交易员都在哪些不同的城市工作过？ (3)
	 * 查找所有来自于剑桥的交易员，并按姓名排序。 (4) 返回所有交易员的姓名字符串，按字母顺序排序。 (5) 有没有交易员是在米兰工作的？ (6)
	 * 打印生活在剑桥的交易员的所有交易额。 (7) 所有交易中，最高的交易额是多少？ (8) 找到交易额最小的交易。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//第一题
		System.out.println(transactions.stream().filter(e -> e.getYear() == 2011)
				.sorted(Comparator.comparing(Transaction::getValue).reversed()).collect(Collectors.toList()));

		System.out.println(transactions.stream().filter(e -> e.getYear() == 2011).sorted((a, b) -> {
			return a.getValue() > b.getValue() ? 1 : 0;
		}).collect(Collectors.toList()));
		
		//第二题
		System.out.println(
		transactions.stream().map(e -> e.getTrader().getCity()).distinct().collect(Collectors.toList())
		);
		
		//第三题
		System.out.println(
				transactions.stream().map(e -> e.getTrader())
				.filter(e -> e.getCity().equals("Cambridge"))
				.distinct()
				.sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList())
				);
		
		// 4
		System.out.println(
				transactions.stream().map(e -> e.getTrader())
				.distinct()
				.map(e -> e.getName()).sorted().collect(Collectors.joining())
				);
		
		//5
		System.out.println(
				transactions.stream().anyMatch(e -> e.getTrader().getCity().equals("Milan"))
				);
		
		// 6
		transactions.stream()
		.filter(e -> e.getTrader().getCity().equals("Cambridge"))
		.map(e -> e.getValue()).forEach(System.out::println);
		
		// 7
		System.out.println(
				transactions.stream().max(Comparator.comparing(Transaction::getValue)).map(e -> e.getValue()).get()
				);
		
		//8
		System.out.println(
				transactions.stream().map(e -> e.getValue()).reduce((a,b) -> a > b ? b : a).get()
				);

		System.out.println(
				transactions.stream().map(e -> e.getValue()).reduce(Integer::min).get()
				);
		
	}
}
