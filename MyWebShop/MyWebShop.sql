create database MyWebShop
go
use MyWebShop
go

create table Kategorija
(
	IDKategorija int primary key identity,
	Naziv nvarchar(50)
)

create table PotKategorija
(
	IDPotKategorija int primary key identity,
	Naziv nvarchar(50),
	KategorijaID int foreign key references Kategorija(IDKategorija)
)


create table Proizvod
(
	IDProizvod int primary key identity,
	Naziv nvarchar(max) not null,
	Sifra nvarchar(7) not null unique,
	CijenaBezPDV money not null,
	PotKategorijaID int foreign key references PotKategorija(IDPotKategorija),
	Opis nvarchar(max),
	Slika nvarchar(max)
)
create table Rola
(
	IDRola int primary key identity,
	Naziv nvarchar(50)
)
create table Korisnik
(
	IDKorisnik  int primary key identity,
	KorisnickoIme nvarchar(max),
	Lozinka nvarchar(max),
	RolaID int foreign key references Rola(IDRola) not null
)

create table Racun
(
	IDRacun int primary key identity,
	DatumIzdavanja datetime not null,	
	UkupnaCijena money not null,
	KorisnikID int foreign key references Korisnik(IDKorisnik) not null,
	BrojRacuna as 'RaèunBr_' + cast(IDRacun as nvarchar(10)),
	NacinPlacanja nvarchar(max)
)


create table Stavka
(
	IDStavka int primary key identity,
	RacunID int foreign key references Racun(IDRacun) not null,
	Kolicina smallint not null,
	ProizvodID int foreign key references Proizvod(IDProizvod),
	CijenaPoKomadu money not null,
	UkupnaCijena money not null
)
create table LogZapis
(
	IDLog int primary key identity,
	KorisnickoIme nvarchar(max),
	DatumVrijemeLogiranja datetime,
	IPAdresa nvarchar(50)
)

--Kategorije
insert into Kategorija values('Raèunala')
insert into Kategorija values('Raèunalne komponente')
insert into Kategorija values('Periferija')

--Potkategorije
insert into PotKategorija values('Laptopi 15"',1)
insert into PotKategorija values('Laptopi 17"',1)
insert into PotKategorija values('Stolna raèunala',1)

insert into PotKategorija values('Procesori',2)
insert into PotKategorija values('Matiène ploèe',2)
insert into PotKategorija values('Memorija',2)

insert into PotKategorija values('Miševi',3)
insert into PotKategorija values('Tipkovnice',3)
insert into PotKategorija values('Web kamere',3)

delete from Proizvod
--Proizvodi
insert into Proizvod values('Notebook Lenovo G5045, 80E300SKSC / AMD E1-6010, 1.35GHz , 2GB DDR3 , 250 GB HDD, 15.6“ LED','0000000',1699.99,1,'Opis','css/images/articles/lenovo15.jpg')
insert into Proizvod values('Notebook HP 250, K3W93EA / DualCore N2840, DVDRW, 4GB, 500GB, HD Graphics, 15.6" LED, LAN, BT, kamera, HDMI, USB 3.0, DOS','0000002',2024.69,1,'Opis','css/images/articles/hp15.jpg')
insert into Proizvod values('Notebook Acer Aspire ES1-512-P0BE, NX.MRWEX.029 / Intel Pentium N3540, 2,16 GHz, Core 4, 4 GB, 15,6", 1366x768, SATA 500 GB, Linux, Intel HD','0000003',2218.04,1,'Opis','css/images/articles/acer15.jpg')

insert into Proizvod values('Tipkovnica + miš Coolermaster Devastator, USB','0000004',269.99,8,'Opis','css/images/articles/tipkovnica1.jpg')
insert into Proizvod values('Tipkovnica + miš Coolermaster Master Octane, USB','0000005',299.99,8,'Opis','css/images/articles/tipkovnica2.jpg')
insert into Proizvod values('Tipkovnica + miš Coolermaster Devastator, USB','0000006',269.99,8,'Opis','css/images/articles/tipkovnica2.jpg')

insert into Proizvod values('Notebook Asus X751MA-TY188D, Intel® Celeron® N2940 (2M Cache, up to 2.25 GHz), RAM 4','0000007',2599.00,2,'Opis','css/images/articles/asus17-1.jpg')
insert into Proizvod values('Notebook Asus X751MA-TY229D, Intel N2940 2.25 GHz, RAM 4 GB DDR3, HDD 1TB 5400, 17,3"','0000008',2699.00,2,'Opis','css/images/articles/asus17-2.jpg')
insert into Proizvod values('Notebook Dell Inspiron 5749, silver, Intel 3805U 1.9GHz, RAM 4GB, 500GB, ntel HD Graphics','0000009',3367.37,2,'Opis','css/images/articles/dell17.jpg')

insert into Proizvod values('Raèunalo Start 2650, AMD X2 2650 1.45GHz, 4GB, 500GB','0000010',1649.00,3,'Opis','css/images/articles/stolno1.jpg')
insert into Proizvod values('Raèunalo LENOVO H520e, 57-324261 + miš i tipkovnica','0000011',1849.00,3,'Opis','css/images/articles/stolno2.jpg')
insert into Proizvod values('Raèunalo Surf X2 4020, AMD 3.40GHz, 8GB, 1000GB, AMD Radeon HD 7480D','0000012',2399.00,3,'Opis','css/images/articles/stolno3.jpg')

insert into Proizvod values('Procesor Intel Celeron G1620, 2.7GHz, Ivy Bridge','0000013',320.71,4,'Opis','css/images/articles/proc1.jpg')
insert into Proizvod values('Procesor AMD Richland A6-Series X2 6400K (3.9GHz,1MB,65W,FM2) box, Black Edition','0000014',434.76,4,'Opis','css/images/articles/proc2.jpg')
insert into Proizvod values('Procesor Intel Core i7 5930K 3.5Ghz,15MB,LGA 2011-3','0000015',579.67,4,'Opis','css/images/articles/proc3.jpg')

insert into Proizvod values('Matièna Asus H81M-K, 1150, Intel® H81 Express Chipset, DDR3','0000016',261.65,5,'Opis','css/images/articles/mbo1.jpg')
insert into Proizvod values('Matièna Asrock Intel 1150 Socket B85 Chipset M-ATX MB','0000116',347.48,5,'Opis','css/images/articles/mbo2.jpg')
insert into Proizvod values('Matièna MSI Z170A XPower Gaming Titanium, s.1151,D4,S3,U3','0000017',498.36,5,'Opis','css/images/articles/mbo3.jpg')

insert into Proizvod values('Memorija Transcend DDR3 2GB 1333MHz, JM1333KLN-2G','0000018',128.07,6,'Opis','css/images/articles/ram1.jpg')
insert into Proizvod values('Patriot Signature,DDR2 800Mhz, 2GB','0000019',206.35,6,'Opis','css/images/articles/ram2.jpg')
insert into Proizvod values('Kingston DDR3 HyperX Fury,1866MHz, 4GB Black','0000020',584.25,6,'Opis','css/images/articles/ram3.jpg')

insert into Proizvod values('NaviaTec USB Mouse 1180','0000021',46.25,7,'Opis','css/images/articles/mis1.jpg')
insert into Proizvod values('MIŠ Speedlink SNAPPY, bežièni tirkizni','0000022',80.40,7,'Opis','css/images/articles/mis2.jpg')
insert into Proizvod values('MS CALIBRE bežièni miš','0000023',530.89,7,'Opis','css/images/articles/mis3.jpg')

insert into Proizvod values('Genius FaceCam 2020, 720p HD kamera
','0000024',75.00,9,'Opis','css/images/articles/webcam1.jpg')

insert into Proizvod values('Web kamera Speedlink Snappy Smart, crna
','0000025',122.44,9,'Opis','css/images/articles/webcam2.jpg')

insert into Proizvod values('HD Webcam C615 EER','0000026',649.99,9,'Opis','css/images/articles/webcam3.jpg')

--Role
insert into Rola values('Admin')
insert into Rola values('User')


--Korisnici
insert into Korisnik values('admin','admin',1)
insert into Korisnik values('pero','pero123',2)

go

create proc GetCategories
as begin
select * from Kategorija
end

go

create proc GetSubCategories 
@idCategory int
as begin
select * from PotKategorija pk
where pk.KategorijaID=@idCategory
end

go

create proc GetArticles
@idSubCategory int
as
begin
select * from Proizvod p
where p.PotKategorijaID=@idSubCategory
end

go

create proc GetArticleDetails
@idArticle int
as
begin
select * from Proizvod p
where @idArticle=p.IDProizvod
end

go

create proc InsertUser
@username nvarchar(max),
@password nvarchar(max)
as
begin
	insert into Korisnik values(@username,@password,2)
end

go
create proc CheckIfCustomerExists
@username nvarchar(max),
@password nvarchar(max)
as
begin
	select count(*) as BrojKorisnika from Korisnik k
	where k.KorisnickoIme=@username
	and 
		k.Lozinka=@password
		and k.RolaID=2
end

go

create proc CheckIfAdminExists
@username nvarchar(max),
@password nvarchar(max)
as
begin
	select count(*) as BrojKorisnika from Korisnik k
	where k.KorisnickoIme=@username
	and 
		k.Lozinka=@password
				and k.RolaID=1
end

go

create proc GetKorisnikID
@username nvarchar(max),
@password nvarchar(max)
as
begin
	select k.IDKorisnik from Korisnik k
	where k.KorisnickoIme=@username
	and
			k.Lozinka=@password
end
go

create proc GetKupci
as
 begin
	select IDKorisnik,KorisnickoIme from Korisnik k
	where k.RolaID=2
	order by KorisnickoIme
 end

go

create proc InsertRacun
@ukupnaCijenaRacuna money,
@korisnikID int,
@nacinPlacanja nvarchar(max),
@idRacun int output
as
begin
	
	insert into Racun(DatumIzdavanja,UkupnaCijena,NacinPlacanja,KorisnikID) 
	values(GETDATE(),@ukupnaCijenaRacuna,@nacinPlacanja,@korisnikID)
	
	set @idRacun=SCOPE_IDENTITY()

end

go

create proc InsertStavka
@racunID int,
@kolicina smallint,
@proizvodID int,
@cijenaPoKomadu money,
@ukupnaCijenaStavke money
as 
begin
	insert into Stavka values(@racunID,@kolicina,@proizvodID,@cijenaPoKomadu,@ukupnaCijenaStavke)
end

go

create proc GetRacuniForKupac
@idKupac int
as 
begin
 select * from Racun r
 where r.KorisnikID=@idKupac
end

go

create proc GetRacunDetalji
@idRacun int
as 
begin
	select * from Racun r
	where r.IDRacun=@idRacun
end

go

create proc GetStavkeForRacun
@idRacun int
as
begin
	select s.CijenaPoKomadu CijenaPoKomadu,
	s.Kolicina Kolicina,
	s.UkupnaCijena UkupnaCijena,
	p.Naziv NazivProizvoda
	 from Stavka s
	inner join Proizvod p on s.ProizvodID=p.IDProizvod
	where s.RacunID=@idRacun
end

go

create proc InsertLog
@username nvarchar(max),
@ipAdress nvarchar(50)
as
begin
	insert into LogZapis values(@username,GETDATE(),@ipAdress)
end

go

create proc GetLogList
@orderColumn nvarchar(max),
@orderDirection nvarchar(20)
as
begin
	select * from LogZapis 
	order by 
	case @orderDirection
          when 'ASC' then
               CASE @orderColumn
			   WHEN 'DatumVrijemeLogiranja' THEN convert(nvarchar(max),DatumVrijemeLogiranja) 
                    WHEN 'KorisnickoIme' THEN KorisnickoIme
                                
               END 			          
     END  ASC,
     case @orderDirection
          when 'DESC' then
               CASE @orderColumn
			   WHEN 'DatumVrijemeLogiranja' THEN convert(nvarchar(max),DatumVrijemeLogiranja) 
                    WHEN 'KorisnickoIme' THEN KorisnickoIme
                                  
               END 			          
     END DESC
end

go

create proc GetRacuniWithFilter
 @idKupac int,
 @startDate date,
 @endDate date
 as
 begin
	if @startDate is null and @endDate is null
	begin
		select * from Racun r
		where r.KorisnikID=@idKupac
	end
	else if @endDate is null
	begin
		select * from Racun r
		where r.KorisnikID=@idKupac and r.DatumIzdavanja > @startDate
	end
	else if @startDate is null
	begin
		select * from Racun r
		where r.KorisnikID=@idKupac and r.DatumIzdavanja < @endDate
	end
	else
	begin
	select * from Racun r
		where r.KorisnikID=@idKupac and r.DatumIzdavanja between @startDate and @endDate
	end
end

go







