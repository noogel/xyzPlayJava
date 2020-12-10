package noogel.xyz.grammar;


import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public class GuavaDemo {
    public static void main(String[] args) {
//        Preconditions.checkArgument(1!=1, "error??");
//        Preconditions.checkState(1!=1, "error??");
        System.out.println(Preconditions.checkNotNull(new GuavaDemo()));
        try{
            Preconditions.checkState(false, "error??");
        } catch (Exception ex) {
//            Throwables.propagateIfPossible(ex, IllegalStateException.class);
        }
        ImmutableSet<String> immutableSet = ImmutableSet.of("a", "b", "c", "d");
        System.out.println(immutableSet);
        ImmutableList<String> immutableList = immutableSet.asList();
        System.out.println(immutableList);
    }
}
