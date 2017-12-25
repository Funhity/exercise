#自动生产新的系统的组织结构code存储过程
DROP PROCEDURE IF EXISTS p_update_org_code;
CREATE PROCEDURE `p_update_org_code`(v_layer int, out as_res int,out as_str varchar(100))
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
label_1:begin

	declare v_done2 int default 0;
	declare v_done3 int default 0;
	declare v_name varchar(50) default null;
	declare v_code varchar(50) default null;
	declare v_parent_code varchar(50) default null;

  declare v_count int default 0;
  declare v_index int default 0;
  declare v_index_str varchar(50) default null;
	declare v_orgid varchar(50) default null;
	declare v_relative_orgid varchar(50) default null;

	set as_res=0;
	set as_str='更新失败';

  select count(name) into v_count from t_rms_organization
				where delete_mark = 1 and layer = v_layer;

	label_2:begin


	    declare cur_2 cursor for select orgid, relative_orgid from t_rms_organization
				where delete_mark = 1 and layer = v_layer;
	    declare continue handler for sqlstate '02000' set v_done2=1;
      open cur_2;
      loop_2:loop
      fetch cur_2 into v_orgid, v_relative_orgid;
        #if v_done2 = 1 then
        #  leave loop_2;
        #end if;

        if v_index >=  v_count then
          leave loop_2;
        end if;

        if v_index < 10 then
          set v_index_str = concat('10', v_index);
        elseif v_index >= 10 and v_index < 100 then
          set v_index_str = concat('1', v_index);
        else
          set v_index_str = v_index;
        end if;

        if LENGTH(v_relative_orgid) > 0 then
          #获取父级code及名称
          select code, name into v_code, v_name from t_rms_organization where orgid = v_relative_orgid ;
          if v_code is not NULL and LENGTH(v_code) > 0 then
            update t_rms_organization set code = concat(v_code, v_index_str), parent_code=v_code, parent_name = v_name where orgid = v_orgid;
          else
            update t_rms_organization set code = concat(code, '_', v_index_str), parent_code='', parent_name = '' where orgid = v_orgid;
          end if;
        else
          update t_rms_organization set code = v_index_str where orgid = v_orgid;
        end if;
        set v_index = v_index + 1;

      end loop loop_2;
      close cur_2;
	end label_2;
	commit;

	set as_res=1;
	set as_str='更新成功';
end label_1
