/** 
 *  Generated by OpenJPA MetaModel Generator Tool.
**/

package com.x.cms.core.entity.element;

import com.x.base.core.entity.SliceJpaObject_;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel
(value=com.x.cms.core.entity.element.ViewFieldConfig.class)
@javax.annotation.Generated
(value="org.apache.openjpa.persistence.meta.AnnotationProcessor6",date="Sat May 06 19:34:34 CST 2017")
public class ViewFieldConfig_ extends SliceJpaObject_  {
    public static volatile SingularAttribute<ViewFieldConfig,Date> createTime;
    public static volatile SingularAttribute<ViewFieldConfig,String> description;
    public static volatile SingularAttribute<ViewFieldConfig,String> editor;
    public static volatile SingularAttribute<ViewFieldConfig,String> fieldName;
    public static volatile SingularAttribute<ViewFieldConfig,String> fieldTitle;
    public static volatile SingularAttribute<ViewFieldConfig,String> id;
    public static volatile SingularAttribute<ViewFieldConfig,String> sequence;
    public static volatile SingularAttribute<ViewFieldConfig,Integer> showSequence;
    public static volatile SingularAttribute<ViewFieldConfig,Date> updateTime;
    public static volatile SingularAttribute<ViewFieldConfig,String> viewId;
}
