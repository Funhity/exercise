#更新信贷系统组织结构父机构名称的存储过程
DROP PROCEDURE IF EXISTS p_set_org_pname;
CREATE PROCEDURE `p_set_org_pname`(out as_res int,out as_str varchar(100))
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

	set as_res=0;
	set as_str='更新失败';


	label_2:begin
		declare cur_2 cursor for select code, parent_code from t_rms_organization
		  where parent_code <> ''
			and parent_name = '';
		declare continue handler for sqlstate '02000' set v_done2=1;
  	open cur_2;
  	loop_2:loop
		fetch cur_2 into v_code, v_parent_code;
			if v_done2 = 1 then
			  leave loop_2;
			end if;
			select name into v_name from t_rms_organization where code = v_parent_code;
			#dbmp_output.put_line(v_name);
			if v_name <> '' and v_name <> null then
				update t_rms_organization set parent_name = v_name where code = v_code;
			end if;
	  end loop loop_2;
	  close cur_2;
	end label_2;

	commit;

	set as_res=1;
	set as_str='更新成功';
end label_1
