package com.huayaoman.qiniu.oss.autoconfigure.properties;

import com.qiniu.common.Zone;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tangcheng
 * 2018/04/12
 */
@ConfigurationProperties("qiniu.oss")
public class QiniuOssProperties {
    private String accessKey;
    private String secretKey;

    private Bucket bucket;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }


    public static class Bucket {
        private String zone;
        private String name;

        public String getZone() {
            return zone;
        }

        public void setZone(String zone) {
            this.zone = zone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public Zone getQiniuZone() {
            if (zone == null) {
                return Zone.autoZone();
            }
            return ZoneEnum.getZone(zone);
        }


        /**
         * reason:OCP
         */
        enum ZoneEnum {
            HUA_DONG("huadong", Zone.zone0()),
            HUA_BEI("huabei", Zone.zone1()),
            HUA_NAN("huanan", Zone.zone2()),

            NORTH_AMERICA("beimei", Zone.zoneNa0()),
            SINGAPORE("xinjiapo", Zone.zoneAs0());

            private String name;
            private Zone zone;

            ZoneEnum(String name, Zone zone) {
                this.name = name;
                this.zone = zone;
            }

            public String getName() {
                return name;
            }

            public Zone getZone() {
                return zone;
            }

            public static Zone getZone(String name) {
                for (ZoneEnum zoneEnum : ZoneEnum.values()) {
                    if (zoneEnum.getName().equalsIgnoreCase(name)) {
                        return zoneEnum.getZone();
                    }
                }
                return Zone.autoZone();
            }


        }

    }

}
