/** 
 *  Generated by OpenJPA MetaModel Generator Tool.
**/

package com.x.processplatform.core.entity.element;

import com.x.base.core.entity.SliceJpaObject_;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel
(value=com.x.processplatform.core.entity.element.QueryStatTimed.class)
@javax.annotation.Generated
(value="org.apache.openjpa.persistence.meta.AnnotationProcessor6",date="Fri Mar 10 10:10:19 CST 2017")
public class QueryStatTimed_ extends SliceJpaObject_  {
    public static volatile SingularAttribute<QueryStatTimed,Date> createTime;
    public static volatile SingularAttribute<QueryStatTimed,Date> expiredTime;
    public static volatile SingularAttribute<QueryStatTimed,String> id;
    public static volatile SingularAttribute<QueryStatTimed,String> project;
    public static volatile SingularAttribute<QueryStatTimed,String> queryStat;
    public static volatile SingularAttribute<QueryStatTimed,Date> scheduleTime;
    public static volatile SingularAttribute<QueryStatTimed,String> sequence;
    public static volatile SingularAttribute<QueryStatTimed,Integer> timingInterval;
    public static volatile SingularAttribute<QueryStatTimed,Date> updateTime;
}