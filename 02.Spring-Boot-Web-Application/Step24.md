## What we will do:
- Remove Hardcoding of User Name
- Remove LoginService
- Rename LoginController to WelcomeController
- Add Logout Functionality

## Useful Snippets
```
	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}

		<ul class="nav navbar-nav navbar-right">
			<li><a href="/logout">Logout</a></li>
		</ul>

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,
			HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}

```
