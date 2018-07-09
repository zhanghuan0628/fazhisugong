package admin_webapp;

import com.ffxl.admin.core.common.constant.Const;
import com.ffxl.admin.core.shiro.ShiroKit;

public class eee {

	public static void main(String[] args) {
		System.out.println(ShiroKit.md5(Const.DEFAULT_PWD, "wdww"));
	}

}
