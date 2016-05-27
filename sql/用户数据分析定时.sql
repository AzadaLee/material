insert into bao_t_cust_analysis_info (id, cust_id, income_amount, record_status, create_user, 
create_date, last_update_user, last_update_date, version, memo ) select sys_guid(), btafi.cust_id, 
nvl(sum(btafi.sumamount), 0), '有效', 'root', to_timestamp('03/22/2016 17:22:18.417', 'mm/dd/yyyy 
hh24:mi:ss.ff3'), 'root', to_timestamp('03/22/2016 17:22:18.417', 'mm/dd/yyyy hh24:mi:ss.ff3'), 
0, NULL from ( select a.cust_id,nvl(sum(a.trade_amount),0) sumamount from bao_t_account_flow_info 
a where a.trade_type in('活期宝收益', '体验宝收益', '定期宝到期收益') group by a.cust_id union all select ri.cust_id,nvl(sum(r.trade_amount),0) 
from bao_t_payment_record_detail r, bao_t_payment_record_info ri where ri.id = r.pay_record_id 
and r.subject_type not in ('风险金垫付本金', '本金') group by ri.cust_id )btafi group by btafi.cust_id 