;
; BIND data file for local loopback interface
;
$TTL	604800
@	IN	SOA	dns.domainprimary.com. root.domainprimary.com. (
			      2		; Serial
			 604800		; Refresh
			  86400		; Retry
			2419200		; Expire
			 604800 )	; Negative Cache TTL
;
; Registros A
domainprimary.com.		A	192.168.1.61
www		A       192.168.1.61
tenda		A       192.168.1.61
api		A       192.168.1.61
ldap		A       192.168.1.61

@	IN	NS	dns.domainprimary.com.
dns.domainprimary.com.	IN	A	192.168.1.61
