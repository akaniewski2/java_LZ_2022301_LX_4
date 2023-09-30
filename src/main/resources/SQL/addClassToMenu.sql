select * from  culinary_recipes
select * from  culinary_ingredients
/**/

select * from  user
select * from  tech_page  
where id=14;

update tech_page  set 
url=concat("/arkani2/",lower(name)),
header=upper(name),
button = header,
class_nm=name
where id=16;
select * from  tech_page ;

update tech_page  set class_nm= replace(replace(CONCAT(UPPER(SUBSTRING(name,1,1)),LOWER(SUBSTRING(name,2))),' ',''),'_','')  

  /* insert into arkani_1.tech_page_role 
   select null,method,'ROLE_GUEST' from  (
   select 'GET' as method from  dual union
   select 'POST' from  dual union
   select 'PUT' from  dual union
   select 'PATCH' from  dual union
   select 'DELETE' from  dual ) s */
   
       delete from arkani_1.goods_rating
       select * from arkani_1.goods_rating
select * from  arkani_1.user
update arkani_1.tech_page  set button = upper(name),header=button;
update arkani_1.tech_page  set project = 'arkani' where project is null;

select * from  arkani_1.tech_page;
 
select * from  arkani_1.tech_page_role;
select * from  arkani_1.tech_page_tech_page_role;

/*numery  w role_id oznaczają role z select * from  arkani_1.tech_page_role*/
/*delete from tech_page_tech_page_role ;*/

select max(id) from  arkani_1.tech_page;
select max(page_id) from  arkani_1.tech_page_tech_page_role;

select distinct page_id from tech_page_tech_page_role order by 1; 
select distinct page_id,role_id from tech_page_tech_page_role where page_id in(10,5);
update tech_page_tech_page_role 
set role_id=-1
where page_id in(10,5) and role_id =11;



insert into tech_page_tech_page_role 
select * from  (
select z.page_id page_id,1 role_id from  dual union 
select z.page_id,2 from  dual union 
select z.page_id,3 from  dual union 
select z.page_id,4 from  dual union 
select z.page_id,5 from  dual union 

select z.page_id,6 from  dual union 
select z.page_id,7 from  dual union 
select z.page_id,8 from  dual union 
select z.page_id,9 from  dual union 
select z.page_id,10 from  dual union 
select z.page_id,11 from  dual 

) as s cross join ( select max(id) as page_id from arkani_1.tech_page  ) as z
   
select * from  tech_page_tech_page_role 

insert into tech_page_tech_page_role 
select * from  (
select :page_id page_id,1 role_id from  dual union 
select :page_id,2 from  dual union 
select :page_id,3 from  dual union 
select :page_id,4 from  dual union 
select :page_id,5 from  dual union 

select :page_id,6 from  dual union 
select :page_id,7 from  dual union 
select :page_id,8 from  dual union 
select :page_id,9 from  dual union 
select :page_id,10 from  dual union 
select :page_id,11 from  dual 

) s

       
       
       
       SELECT ("val1", "val2", Numbers.Number-1)
FROM Numbers
WHERE Numbers.Number <= 10;
       
/*
 -- # sql convert to class
 **/
       
select  ordinal_position,concat(javaDataType,' ',column_name,sep) output
from  (
select 
replace(replace(replace(data_type,'bigint','long'),'varchar','String'),'text','String') javaDataType,
column_name,
';' sep,data_type,ordinal_position
from  information_schema.columns where lower(table_name)='zakupy') s
 order by ordinal_position


 
 package pl.arkani.LZ_2022301_LX.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;

package pl.arkani.LZ_2022301_LX.model;

import jakarta.persistence.*;
import lombok.*;
 

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import javax.validation.constraints.NotBlank;


@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Zakupy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    // @Column(name="`desc`")
    @NotBlank(message = "name is mandatory")
    private String name;

    private String date;


}

select * from  movie
select * from  purchase_category
