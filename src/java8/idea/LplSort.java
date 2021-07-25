package java8.idea;

import java8.chapter5.Transaction;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author nuc
 */
public class LplSort {

    public static void main(String[] args) {
        ArrayList<LplTeam> list = new ArrayList<>();

        list.add(new LplTeam("we",0,8,4,6));
        list.add(new LplTeam("tes",0,7,4,6));
        list.add(new LplTeam("sn",0,7,6,4));
        list.add(new LplTeam("jdg",0,7,6,3));
        list.add(new LplTeam("blg",0,7,6,3));
        list.add(new LplTeam("edg",0,10,3,13));
        list.add(new LplTeam("fpx",0,10,3,12));
        list.add(new LplTeam("ra",0,9,4,9));
        list.add(new LplTeam("lng",0,9,4,7));
        list.add(new LplTeam("ig",0,4,7,-5));
        list.add(new LplTeam("omg",0,7,6,-1));
        list.add(new LplTeam("lgd",0,6,8,-3));
        list.add(new LplTeam("rw",0,4,9,-11));
        list.add(new LplTeam("up",0,3,-10,-14));
        list.add(new LplTeam("tt",0,2,10,-14));
        list.add(new LplTeam("v5",0,0,12,-22));
        list.add(new LplTeam("rng",0,7,5,7));

        Collections.shuffle(list);
        TreeMap<Integer, TreeMap<Integer, TreeMap<Integer, List<LplTeam>>>> collect = list.stream().collect(Collectors.groupingBy(LplTeam::getWin, () -> new TreeMap<>(Comparator.reverseOrder()),
                Collectors.groupingBy(LplTeam::getLoss, TreeMap::new,
                        Collectors.groupingBy(LplTeam::getSource, () -> new TreeMap<>(Comparator.reverseOrder()), Collectors.toList()))));

        ArrayList<LplTeam> Lplteams = new ArrayList<>();

        final int[] sort = {1};
        collect.entrySet().stream().forEach(win -> {
            win.getValue().entrySet().stream().forEach(loes -> {
                loes.getValue().entrySet().stream().forEach(source -> {
                    int init = sort[0];
                    List<LplTeam> value = source.getValue();
                    value.forEach(e -> e.setSort(init));
                    Lplteams.addAll(source.getValue());
                    int size = value.size() > 1 ? value.size() : 1;
                    sort[0]+= size ;
                });
            });
        });

        Lplteams.forEach(e -> System.out.println(e.toString()));

    }
}
