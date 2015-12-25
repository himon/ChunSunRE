package com.chunsun.redenvelope.entities.json;

import com.chunsun.redenvelope.entities.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/8/20.
 */
public class BalanceListEntity extends BaseEntity {


    /**
     * result : {"logs":[{"status":1,"remark":"条微信的红包奖励","value":"+0.03","complete_time":"2015/8/13 18:03:16","add_time":"2015/8/13 18:03:16","status_title":"已完成"},{"status":1,"remark":"不要向的红包奖励","value":"+0.02","complete_time":"2015/8/13 10:53:26","add_time":"2015/8/13 10:53:26","status_title":"已完成"},{"status":1,"remark":"精准高效营销 微商必备利器的红包奖励","value":"+0.10","complete_time":"2015/8/8 16:18:32","add_time":"2015/8/8 16:18:32","status_title":"已完成"},{"status":1,"remark":"宝宝穿金带银的红包奖励","value":"+0.02","complete_time":"2015/8/7 10:23:21","add_time":"2015/8/7 10:23:21","status_title":"已完成"},{"status":1,"remark":"精准高效营销 微商必备利器的红包奖励","value":"+0.10","complete_time":"2015/8/7 10:08:34","add_time":"2015/8/7 10:08:34","status_title":"已完成"},{"status":1,"remark":"Baidu的红包奖励","value":"+0.03","complete_time":"2015/8/6 16:08:21","add_time":"2015/8/6 16:08:21","status_title":"已完成"},{"status":1,"remark":"奖励的红包奖励","value":"+0.03","complete_time":"2015/8/5 15:54:15","add_time":"2015/8/5 15:54:15","status_title":"已完成"},{"status":1,"remark":"awdeawyyt的红包奖励","value":"+0.03","complete_time":"2015/8/5 15:48:29","add_time":"2015/8/5 15:48:29","status_title":"已完成"},{"status":1,"remark":"11的红包奖励","value":"+0.03","complete_time":"2015/8/4 15:26:24","add_time":"2015/8/4 15:26:24","status_title":"已完成"},{"status":1,"remark":"12的红包奖励","value":"+0.03","complete_time":"2015/8/4 15:26:20","add_time":"2015/8/4 15:26:20","status_title":"已完成"},{"status":1,"remark":"8的红包奖励","value":"+0.03","complete_time":"2015/8/4 15:26:12","add_time":"2015/8/4 15:26:12","status_title":"已完成"},{"status":1,"remark":"18的红包奖励","value":"+0.03","complete_time":"2015/8/4 15:26:07","add_time":"2015/8/4 15:26:07","status_title":"已完成"},{"status":1,"remark":"17的红包奖励","value":"+0.03","complete_time":"2015/8/4 15:26:03","add_time":"2015/8/4 15:26:03","status_title":"已完成"},{"status":1,"remark":"15的红包奖励","value":"+0.03","complete_time":"2015/8/4 15:25:57","add_time":"2015/8/4 15:25:57","status_title":"已完成"},{"status":1,"remark":"14的红包奖励","value":"+0.03","complete_time":"2015/8/4 15:25:53","add_time":"2015/8/4 15:25:53","status_title":"已完成"}],"total_count":"43"}
     */
    private ResultEntity result;

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public ResultEntity getResult() {
        return result;
    }

    public static class ResultEntity {
        /**
         * logs : [{"status":1,"remark":"条微信的红包奖励","value":"+0.03","complete_time":"2015/8/13 18:03:16","add_time":"2015/8/13 18:03:16","status_title":"已完成"},{"status":1,"remark":"不要向的红包奖励","value":"+0.02","complete_time":"2015/8/13 10:53:26","add_time":"2015/8/13 10:53:26","status_title":"已完成"},{"status":1,"remark":"精准高效营销 微商必备利器的红包奖励","value":"+0.10","complete_time":"2015/8/8 16:18:32","add_time":"2015/8/8 16:18:32","status_title":"已完成"},{"status":1,"remark":"宝宝穿金带银的红包奖励","value":"+0.02","complete_time":"2015/8/7 10:23:21","add_time":"2015/8/7 10:23:21","status_title":"已完成"},{"status":1,"remark":"精准高效营销 微商必备利器的红包奖励","value":"+0.10","complete_time":"2015/8/7 10:08:34","add_time":"2015/8/7 10:08:34","status_title":"已完成"},{"status":1,"remark":"Baidu的红包奖励","value":"+0.03","complete_time":"2015/8/6 16:08:21","add_time":"2015/8/6 16:08:21","status_title":"已完成"},{"status":1,"remark":"奖励的红包奖励","value":"+0.03","complete_time":"2015/8/5 15:54:15","add_time":"2015/8/5 15:54:15","status_title":"已完成"},{"status":1,"remark":"awdeawyyt的红包奖励","value":"+0.03","complete_time":"2015/8/5 15:48:29","add_time":"2015/8/5 15:48:29","status_title":"已完成"},{"status":1,"remark":"11的红包奖励","value":"+0.03","complete_time":"2015/8/4 15:26:24","add_time":"2015/8/4 15:26:24","status_title":"已完成"},{"status":1,"remark":"12的红包奖励","value":"+0.03","complete_time":"2015/8/4 15:26:20","add_time":"2015/8/4 15:26:20","status_title":"已完成"},{"status":1,"remark":"8的红包奖励","value":"+0.03","complete_time":"2015/8/4 15:26:12","add_time":"2015/8/4 15:26:12","status_title":"已完成"},{"status":1,"remark":"18的红包奖励","value":"+0.03","complete_time":"2015/8/4 15:26:07","add_time":"2015/8/4 15:26:07","status_title":"已完成"},{"status":1,"remark":"17的红包奖励","value":"+0.03","complete_time":"2015/8/4 15:26:03","add_time":"2015/8/4 15:26:03","status_title":"已完成"},{"status":1,"remark":"15的红包奖励","value":"+0.03","complete_time":"2015/8/4 15:25:57","add_time":"2015/8/4 15:25:57","status_title":"已完成"},{"status":1,"remark":"14的红包奖励","value":"+0.03","complete_time":"2015/8/4 15:25:53","add_time":"2015/8/4 15:25:53","status_title":"已完成"}]
         * total_count : 43
         */
        private List<LogsEntity> logs;
        private String total_count;

        public void setLogs(List<LogsEntity> logs) {
            this.logs = logs;
        }

        public void setTotal_count(String total_count) {
            this.total_count = total_count;
        }

        public List<LogsEntity> getLogs() {
            return logs;
        }

        public String getTotal_count() {
            return total_count;
        }

        public static class LogsEntity {
            /**
             * status : 1
             * remark : 条微信的红包奖励
             * value : +0.03
             * complete_time : 2015/8/13 18:03:16
             * add_time : 2015/8/13 18:03:16
             * status_title : 已完成
             */
            private int status;
            private String remark;
            private String value;
            private String complete_time;
            private String add_time;
            private String status_title;
            /**
             * adapter显示的类型
             */
            private int type;

            public void setStatus(int status) {
                this.status = status;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public void setComplete_time(String complete_time) {
                this.complete_time = complete_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public void setStatus_title(String status_title) {
                this.status_title = status_title;
            }

            public int getStatus() {
                return status;
            }

            public String getRemark() {
                return remark;
            }

            public String getValue() {
                return value;
            }

            public String getComplete_time() {
                return complete_time;
            }

            public String getAdd_time() {
                return add_time;
            }

            public String getStatus_title() {
                return status_title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
