<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="Content-Type" content="text/html">
<link href="https://fonts.googleapis.com/css?family=Jura"
	rel="stylesheet">
<!--  <link rel="stylesheet" type="text/css" href="CSS/stylecss.css"> -->
<link rel="stylesheet" type="text/css" href="CSS/stylecss.css">

<title>Main Page</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
<fmt:message bundle="${loc}" key="local.login" var="login" />
<fmt:message bundle="${loc}" key="local.password" var="password" />
<fmt:message bundle="${loc}" key="local.signIn" var="signIn" />
<fmt:message bundle="${loc}" key="local.register" var="register" />

<fmt:message bundle="${loc}" key="local.payment" var="payment" />
<fmt:message bundle="${loc}" key="local.news" var="news" />
<fmt:message bundle="${loc}" key="local.results" var="results" />
<fmt:message bundle="${loc}" key="local.about" var="about" />
<fmt:message bundle="${loc}" key="local.language" var="language" />

</head>
<body>
	<header>
		<section>
			<div class="top-area">
				<ul class="topnav" id="myTopnav">
					<li><a href="#payment"><b>${payment }</b></a></li>
					<li><a href="#news"><b>${news }</b></a></li>
					<li><a href="#results"><b>${results }</b></a></li>
					<li><a href="#about"><b>${about }</b></a></li>
				</ul>

				<div class="dropdown">
					<button class="dropbtn">${language }</button>
					<div class="dropdown-content">

						<form action="Controller" method="post">
							<input type="hidden" name="command" value="change-language" />
							<div>
								<input type="hidden" name="local" value="ru" />
								<div>
									<input type="submit" class="dropBtn" value="${ru_button}" />
								</div>
							</div>
						</form>

						<form action="Controller" method="post">
							<div>
								<input type="hidden" name="command" value="change-language" />
							</div>

							<input type="hidden" name="local" value="en" /> <input
								type="submit" value="${en_button}" /><br />
						</form>
					</div>
				</div>
				<form action="Controller" method="get" name="registration">
					<input type="hidden" name="command" value="go-to-registration" />
					<div class="user">
						<input class="btn-register" type="submit" value="${register}">
					</div>
				</form>

				<form action="Controller" method="post" name="sign-in">
					<div>
						<input type="hidden" name="command" value="sign-in" />
						<div class="user">
							<input type="text" name="login" id="login" placeholder="${login}"
								required />
						</div>
						<div class="user">
							<input type="password" name="password" id="password"
								placeholder="${password}" required />
						</div>
						<div class="user">
							<input class="btn-login" type="submit" value="${signIn}">
						</div>

					</div>
				</form>
				<c:if test="${not empty sessionScope.result}">
					<c:if test="${sessionScope.result }">
						<c:out value="You registrated successfully." />
					</c:if>

				</c:if>
			</div>
		</section>
	</header>

	<aside class="sidebar-right">
		<article>
			<h4>
				<a
					href="https://www.overbetting.net/news/agames/unibet-dostig-istoricheskogo-maksimuma-rentabelnosti.html">
					Unibet достиг исторического максимума рентабельности </a>
			</h4>
			<p>Корпорация Unibet сообщила о рекордной рентабельности в
				третьем квартале 2016 года: валовой доход достиг максимума £142 300
				000, увеличившись в годовом исчислении на 65%.</p>
			<time datetime="2016-11-04">11.04.2016</time>
		</article>

		<article>
			<h4>
				<a
					href="https://www.overbetting.net/news/agames/deutsche-bank-prodast-aktsii-kazino-red-rock-na-400-000-000.html">
					Deutsche Bank продаст акции казино Red Rock на $400 000 000 </a>
			</h4>
			<p>Банк Deutsche Bank готовится продать пакет акций игорной
				корпорации Red Rock (Лас-Вегас) за $400 000 000. Газета Financial
				Times сообщает, что это придаст ему «ускорение» в период, когда
				сохраняются опасения по поводу состояния его баланса.</p>
			<time datetime="2016-11-04">11.04.2016</time>
		</article>
	</aside>


	<!-- <main class="content"> -->
	<div class="content">
	<article>
		<h2 class="rules">Правила Тотализатора</h2>
		<section>
			<h4>Общие положения</h4>
			<p>
				<b>Тотализатор</b> - это игра и заведение, где организатор на
				основании правил тотализатора и регулирующего этот вид деятельности
				законодательства принимает денежные ставки от участников на исходы
				представленных тотализатором соревнований, распределяет согласно
				правил полученные в виде ставок денежные средства (пул) между
				выигравшими участниками, удерживая себе фиксированный процент (доход
				тотализатора) для компенсации издержек и получения прибыли.
			</p>
			<p>
				<b>Участник тотализатора</b> - физическое лицо, сделавшее ставку в
				тотализаторе в порядке и на условиях, определяемых настоящими
				правилами. Участник тотализатора должен быть не моложе 18 лет.
			</p>
			<p>
				<b>Событие</b> – здесь то же, что матч, соревнованиеСтавка в
				тотализаторе - совокупность исходов событий (по одному из каждого
				матча очередного тотализатора), на которую участник ставит деньги, а
				также величина этой денежной суммы. Участник выигрывает по ставке,
				если в ней угаданы результаты 12 и более событий, и проигрывает,
				если угадано менее 12 событий. При проигрыше ставка теряется, при
				выигрыше - входит в выплату участнику из призового фонда как его
				составная часть.
			</p>
			<b>Пул</b> -денежная сумма, полученная в виде ставок участников на
			очередной розыгрыш тотализатора.
		</section>

		<section>
			<h4>Правила рассчета призового фонда</h4>
			<p>
				<b>Призовой фонд</b> - часть пула (90%), предназначенная для выплаты
				выигрышей. Призовой фонд разделяется на 4 выигрышные категории:
			</p>
			<ul>
				<li>Первая категория – <b>20% от пула</b>, в распределении этой
					части призового фонда участвуют ставки, в которых угаданы
					результаты 15 событий.
				</li>
				<li>Вторая категория – <b>15% от пула</b>, в распределении
					участвуют все ставки, где угаданы результаты 15 или 14 событий.
				</li>
				<li>Третья категория – <b>20% от пула</b>, в распределении
					участвуют все ставки, где угаданы результаты 15, 14 или 13 событий.
				</li>
				<li>Четвертая категория – <b>35% от пула</b>, в распределении
					участвуют все ставки, где угаданы результаты 15, 14, 13 или 12
					событий.
				</li>
				<li>Джек-пот - дополнительный выигрыш, который распределяется
					среди ставок, где угаданы результаты 15 матчей.</li>
			</ul>
		</section>
		<section>
			<h4>Преимущества ставок через интернет</h4>
			<p>У заключения пари на спортивные события в режиме онлайн
				существует огромное количество плюсов. Самыми значимыми из них
				являются:</p>
			<ol>
				<li>Удобство для пользователя. Клиенту не нужно плестись через
					весь город в офис конторы и проталкиваться сквозь толпу к кассе,
					чтобы сделать ставку или получить выигрыш.</li>
				<li>Экономия времени. Для того чтобы сделать ставку через
					интернет, достаточно зайти на сайт и выбрать нужное событие всего
					за пару секунд. Раньше для подобных операций требовалось не менее
					получаса. И это без учета времени, потраченного на дорогу к офису.</li>
				<li>Больше возможностей. Сайты онлайн тотализаторов постоянно
					расширяют свой спектр услуг, чтобы угодить каждому клиенту. И порой
					они предлагают куда больше вариантов сделок, чем их реальные
					представительства. Помимо этого клиенты, которые работают с
					тотализатором во всемирной сети, получают доступ к виртуальному
					казино, покерным комнатам и большому количеству других азартных
					развлечений.</li>
			</ol>
		</section>
	</article>
	<!-- </main> -->
	</div>
	<footer class="bottom">
		<p>&copy;All rights reserved. Totalizator by Andrey Kryshtapovich</p>

		<section>
			<p>Наш адрес электронной почты info@toto.com, телефон
				контакт-центра: 8 (800) 77-56-21.</p>
		</section>

		<section>
			<a href="https://www.facebook.com"> <img
				src="IMG/facebook_logo.jpg" alt="Facebook" width="25" height="25">
			</a> <a href="https://www.twitter.com"> <img
				src="IMG/twitter_logo.jpg" alt="Twitter" width="25" height="25">
			</a>
		</section>
	</footer>
</body>
</html>
