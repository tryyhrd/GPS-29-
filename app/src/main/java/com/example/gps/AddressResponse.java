package com.example.gps;

import java.util.List;

public class AddressResponse {
    public Response response;

    public static class Response {
        public GeoObjectCollection GeoObjectCollection;
        public static class GeoObjectCollection {
            public List<FeatureMember> featureMember;
            public static class FeatureMember {
                public GeoObject GeoObject;
                public static class GeoObject {
                    public MetaDataProperty metaDataProperty;
                    public static class MetaDataProperty {
                        public GeocoderMetaData GeocoderMetaData;
                        public static class GeocoderMetaData {
                            public String text;
                        }
                    }
                }
            }
        }

    }
}
