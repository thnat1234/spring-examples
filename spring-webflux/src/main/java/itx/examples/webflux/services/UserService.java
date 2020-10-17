package itx.examples.webflux.services;

import itx.examples.webflux.dto.UserData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<UserData> getEmployee(String id);

    Flux<UserData> getAll();

    Mono<UserData> update(UserData employee);

    Mono<UserData> delete(String id);

}