package com.theironyard.clt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HibernatinFreeController {

    @Autowired
    GameRepository games;

    @Autowired
    UserRepository users;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model, String genre, Integer releaseYear, Integer page) {
        page = (page == null) ? 0 : page;
        PageRequest pr = new PageRequest(page, 10);
        Page<Game> gameList;

        String userName = (String) session.getAttribute("userName");
        User user = users.findFirstByUserName(userName);
        if (user != null) {
            model.addAttribute("user", user);
        }


        if (genre != null) {
            gameList = games.findByGenre(pr, genre);
        } else if (releaseYear != null) {
            gameList = games.findByReleaseYear(pr, releaseYear);
        } else {
            gameList = games.findAll(pr);
        }
        model.addAttribute("games", gameList);
        return "home";
    }

    @RequestMapping(path = "/add-game", method = RequestMethod.POST)
    public String addGame(HttpSession session, String gameName, String gamePlatform, String gameGenre, int gameYear) {
        String userName = (String) session.getAttribute("userName");
        User user = users.findFirstByUserName(userName);
        Game game = new Game(gameName, gameGenre, gamePlatform, gameYear, user);
        games.save(game);
        return "redirect:/";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String passwordHash) throws Exception {
        User user = users.findFirstByUserName(userName);
        if (user == null) {
            user = new User(userName, PasswordStorage.createHash(passwordHash));
            users.save(user);
        }
        else if (!PasswordStorage.verifyPassword(passwordHash, user.passwordHash)) {
            throw new Exception("Incorrect passwordHash");
        }
        session.setAttribute("userName", userName);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


}
