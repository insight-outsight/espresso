package org.ootb.espresso.facilities.beanutils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BeanCopyUtilTest {

    Logger log = LoggerFactory.getLogger(BeanCopyUtilTest.class);
    
    int repeatTimes = 20_0000;
    
    @Test
    public void testCopyList2() {
        for (int i = 0; i < repeatTimes; i++) {
            List<UserDO> userDOList = new ArrayList<>();
            userDOList.add(new UserDO(1L, "Van", 18, 1));
            userDOList.add(new UserDO(2L, "VanVan", 28, 2));
            List<UserVO> copyList =  CopyListPropertiesUtils.copyListProperties(userDOList, UserVO.class);
//            System.out.println("userVOList:" + copyList);
        }
    }
    @Test
    public void testCopyList3() {
        for (int i = 0; i < repeatTimes; i++) {
            List<UserDO> userDOList = new ArrayList<>();
            userDOList.add(new UserDO(1L, "Van", 18, 1));
            userDOList.add(new UserDO(2L, "VanVan", 28, 2));
            List<UserVO> copyList =  CopyListBeanUtils.copyListProperties(userDOList, UserVO::new, 
                    (userDO, userVO) ->{
                        userVO.setSex(userDO.getSex() + "-s");
                    });
//            System.out.println("userVOList:" + copyList);
        }
    }

}
