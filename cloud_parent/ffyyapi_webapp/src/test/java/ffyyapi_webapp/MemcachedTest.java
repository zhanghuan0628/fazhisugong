package ffyyapi_webapp;

import com.ffxl.platform.util.MemcachedUtils;

public class MemcachedTest {

    public static void main(String[] args) {
        MemcachedUtils.add("jiawei", "贾威");
        System.out.println("缓存值："+MemcachedUtils.get("jiawei"));
    }

}
