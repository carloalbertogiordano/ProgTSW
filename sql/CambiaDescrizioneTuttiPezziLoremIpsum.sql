USE Ecommerce;

SET sql_safe_updates=0;
UPDATE Pezzo
	SET Descrizione="bibendum enim facilisis gravida neque convallis a cras semper auctor neque vitae tempus quam pellentesque nec nam aliquam sem et tortor consequat id porta nibh venenatis cras sed felis eget"
	where  Descrizione is null;
    
SET sql_safe_updates=1;