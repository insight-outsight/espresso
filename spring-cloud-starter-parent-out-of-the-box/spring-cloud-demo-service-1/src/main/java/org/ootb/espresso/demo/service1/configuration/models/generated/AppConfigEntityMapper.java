package org.ootb.espresso.demo.service1.configuration.models.generated;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

public interface AppConfigEntityMapper {

    @Select({
        "select * from rc_app_config"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="android_income_switch", property="androidIncomeSwitch", jdbcType=JdbcType.TINYINT),
        @Result(column="ios_income_switch", property="iosIncomeSwitch", jdbcType=JdbcType.TINYINT),
        @Result(column="android_operation_switch", property="androidOperationSwitch", jdbcType=JdbcType.TINYINT),
        @Result(column="ios_operation_switch", property="iosOperationSwitch", jdbcType=JdbcType.TINYINT),
        @Result(column="operation_time", property="operationTime", jdbcType=JdbcType.INTEGER),
        @Result(column="android_version", property="androidVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="ios_version", property="iosVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="interval_hour", property="intervalHour", jdbcType=JdbcType.INTEGER),
        @Result(column="show_number", property="showNumber", jdbcType=JdbcType.INTEGER),
        @Result(column="next_time", property="nextTime", jdbcType=JdbcType.INTEGER),
        @Result(column="boy_active_auto", property="boyActiveAuto", jdbcType=JdbcType.TINYINT),
        @Result(column="girl_active_auto", property="girlActiveAuto", jdbcType=JdbcType.TINYINT),
        @Result(column="hot_user_min_stone_num", property="hotUserMinStoneNum", jdbcType=JdbcType.INTEGER),
        @Result(column="top_user_num", property="topUserNum", jdbcType=JdbcType.INTEGER),
        @Result(column="top_switch", property="topSwitch", jdbcType=JdbcType.TINYINT),
        @Result(column="active_switch", property="activeSwitch", jdbcType=JdbcType.TINYINT),
        @Result(column="home_snapshot", property="homeSnapshot", jdbcType=JdbcType.INTEGER),
        @Result(column="invitation_gold_num", property="invitationGoldNum", jdbcType=JdbcType.DECIMAL),
        @Result(column="match_wait_time", property="matchWaitTime", jdbcType=JdbcType.TINYINT),
        @Result(column="platform_type", property="platformType", jdbcType=JdbcType.TINYINT),
        @Result(column="match_quit_time", property="matchQuitTime", jdbcType=JdbcType.INTEGER),
        @Result(column="match_friend_button_time", property="matchFriendButtonTime", jdbcType=JdbcType.INTEGER),
        @Result(column="match_like_button_time", property="matchLikeButtonTime", jdbcType=JdbcType.INTEGER),
        @Result(column="app_id", property="appId", jdbcType=JdbcType.INTEGER),
        @Result(column="gold_show_match_time", property="goldShowMatchTime", jdbcType=JdbcType.INTEGER),
        @Result(column="sex_selection_match_time", property="sexSelectionMatchTime", jdbcType=JdbcType.INTEGER),
        @Result(column="girl_gap_match_time", property="girlGapMatchTime", jdbcType=JdbcType.INTEGER),
        @Result(column="request_no_time", property="requestNoTime", jdbcType=JdbcType.INTEGER),
        @Result(column="request_time", property="requestTime", jdbcType=JdbcType.INTEGER),
        @Result(column="text_translate", property="textTranslate", jdbcType=JdbcType.TINYINT),
        @Result(column="video_translate", property="videoTranslate", jdbcType=JdbcType.TINYINT),
        @Result(column="bury_record_switch", property="buryRecordSwitch", jdbcType=JdbcType.TINYINT),
        @Result(column="index_commodity_switch", property="indexCommoditySwitch", jdbcType=JdbcType.TINYINT),
        @Result(column="goddess_not_match_time", property="goddessNotMatchTime", jdbcType=JdbcType.INTEGER),
        @Result(column="goddess_quit_time", property="goddessQuitTime", jdbcType=JdbcType.INTEGER),
        @Result(column="goddess_event_record_switch", property="goddessEventRecordSwitch", jdbcType=JdbcType.TINYINT),
        @Result(column="friend_bury_record_switch", property="friendBuryRecordSwitch", jdbcType=JdbcType.TINYINT),
        @Result(column="bury_record_switch_all", property="buryRecordSwitchAll", jdbcType=JdbcType.TINYINT),
        @Result(column="h5_switch", property="h5Switch", jdbcType=JdbcType.TINYINT),
        @Result(column="web_pay_tips", property="webPayTips", jdbcType=JdbcType.TINYINT),
        @Result(column="web_pay_tips_country", property="webPayTipsCountry", jdbcType=JdbcType.VARCHAR),
        @Result(column="goddess_video_show_range", property="goddessVideoShowRange", jdbcType=JdbcType.VARCHAR),
        @Result(column="recharge_activity_switch", property="rechargeActivitySwitch", jdbcType=JdbcType.TINYINT),
        @Result(column="back_time", property="backTime", jdbcType=JdbcType.TINYINT),
        @Result(column="translate_times", property="translateTimes", jdbcType=JdbcType.TINYINT),
        @Result(column="match_video_filter_switch", property="matchVideoFilterSwitch", jdbcType=JdbcType.TINYINT),
        @Result(column="other_video_filter_switch", property="otherVideoFilterSwitch", jdbcType=JdbcType.TINYINT),
        @Result(column="third_pay_redirect_url", property="thirdPayRedirectUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="ad_switch", property="adSwitch", jdbcType=JdbcType.TINYINT),
        @Result(column="recommend_switch", property="recommendSwitch", jdbcType=JdbcType.TINYINT),
        @Result(column="mobile_login_switch", property="mobileLoginSwitch", jdbcType=JdbcType.TINYINT),
        @Result(column="free_hand_time", property="freeHandTime", jdbcType=JdbcType.INTEGER),
        @Result(column="pay_hand_time", property="payHandTime", jdbcType=JdbcType.INTEGER),
        @Result(column="quick_chat_free_time", property="quickChatFreeTime", jdbcType=JdbcType.INTEGER)
    })
    List<AppConfigEntity> selectAll();
   
}