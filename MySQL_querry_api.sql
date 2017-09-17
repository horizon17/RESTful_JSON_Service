SELECT name, count, `date`, lang 
FROM hh_parser.tech
Where `date`> '2017-08-03'
Order by count Desc

// попытка вложенного:
Select date, t2.name, t2.count
FROM
tech
Where
date IN (SELECT date, name, sum(count) as count, lang FROM tech WHERE lang='java' Group by date, name Order by date, count Desc LIMIT 10) t2


// попытка обычного:
Select s1.date, s2.name, s2.count
FROM
(SELECT date FROM tech WHERE lang='java' Group by date Order by date) s1
Left Join
(SELECT date, name, sum(count) as count, lang FROM tech WHERE lang='java' Group by date, name Order by date, sum(count) Desc LIMIT 10) s2
On s1.date=s2.date

