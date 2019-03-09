package bot.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DynamicCommandBase<T> {

    private List<String> commands = new ArrayList<>();

}
