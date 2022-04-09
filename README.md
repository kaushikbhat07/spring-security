## Notes

1) Interact with `AuthenticationManagerBuilder`, which indirectly interacts
with `AuthenticationManager`. Override `configure` method that passes `AuthenticationManagerBuilder` as a
parameter. Manipulate the argument to override authentication configuration. 

![img.png](img.png)

2) Override `configure` method that passes `HttpSecurity` to configure Authorization.

![img_1.png](img_1.png)
![img_2.png](img_2.png)
![img_3.png](img_3.png)

3) Internal working

![img_4.png](img_4.png)

4) JDBC authentication: Override `configure` method and set the data source. Create users and authorities table and
populate them.
