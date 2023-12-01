use db_gws;

select MIN(custo) from tb_demandas;
select MAX(custo) from tb_demandas;

select titulo, custo
from tb_demandas
where custo = (select MIN(custo) from tb_demandas);

select COUNT(id_demanda) from tb_demandas;

select COUNT(id_demanda), status
from tb_demandas
where status = "Em andamento";

select COUNT(id_demanda), status
from tb_demandas
group by status;

select SUM(custo) from tb_demandas;

select * from tb_demandas where tb_demandas.titulo LIKE "Registro de maquinas";

select titulo from tb_demandas order by titulo asc;
select titulo from tb_demandas order by titulo desc;

/*INNER JOIN*/
select titulo, tb_usuarios.nome
from tb_demandas
inner join tb_usuarios; 
