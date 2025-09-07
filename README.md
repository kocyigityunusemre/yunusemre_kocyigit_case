# Insider Selenium Task

Insider Selenium Task
Proje Açıklaması

Bu proje, Insider kariyer sayfasındaki Quality Assurance (QA) ilanlarını kontrol etmek için hazırlanmıştır.
Selenium + Java + TestNG kullanılmıştır.

Test Senaryosu

1. Insider ana sayfası açılır ve sayfanın açıldığı doğrulanır.

2. Menüden Company → Careers sayfasına gidilir.

   Locations, Teams ve Life at Insider bloklarının görünür olduğu kontrol edilir.

3. QA Jobs sayfasına geçilir ve See all QA jobs butonuna tıklanır.

4.Filtreleme yapılır:

 -Location: Istanbul, Turkey

 -Department: Quality Assurance

 -Liste boş değil ve tüm ilanların pozisyon/department/location alanları doğru içerikte olduğu doğrulanır.

5. İlk iş ilanında View Role butonuna tıklanır ve açılan sayfanın Lever başvuru formu olduğuna dair kontrol yapılır.
---

## Kullanılan Teknolojiler
- Java 17  
- Selenium 4.22  
- TestNG 7.10  
- Maven  
- WebDriverManager  

---

## Kurulum & Çalıştırma

1. Repoyu bilgisayarınıza klonlayın:
   ```bash
   git clone https://github.com/kocyigityunusemre/yunusemre_kocyigit_case.git

2.CMD (Komut İstemi) açıp proje klasörüne gidin:

cd yunusemre_kocyigit_case

3.Maven ile testleri çalıştırın:

 mvn clean test
