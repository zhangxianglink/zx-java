package java8.idea;

import java.util.Objects;

/**
 * @author nuc
 */
public class LplTeam {

    private String name;

    private Integer sort;

    private Integer win;

    private Integer loss;

    private Integer source;

    public LplTeam(String name, Integer sort, Integer win, Integer loss, Integer source) {
        this.name = name;
        this.sort = sort;
        this.win = win;
        this.loss = loss;
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getWin() {
        return win;
    }

    public void setWin(Integer win) {
        this.win = win;
    }

    public Integer getLoss() {
        return loss;
    }

    public void setLoss(Integer loss) {
        this.loss = loss;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LplTeam lplTeam = (LplTeam) o;
        return Objects.equals(name, lplTeam.name) && Objects.equals(sort, lplTeam.sort) && Objects.equals(win, lplTeam.win) && Objects.equals(loss, lplTeam.loss) && Objects.equals(source, lplTeam.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sort, win, loss, source);
    }

    @Override
    public String toString() {
        return "LplTeam{" +
                "name='" + name + '\'' +
                ", sort=" + sort +
                ", win=" + win +
                ", loss=" + loss +
                ", source=" + source +
                '}';
    }
}
