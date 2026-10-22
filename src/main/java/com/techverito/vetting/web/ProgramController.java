package com.techverito.vetting.web;

import com.techverito.vetting.service.ProgramService;
import com.techverito.vetting.service.ProgramService.VettingSettingsUpdate;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {

    private final ProgramService programs;

    public ProgramController(ProgramService programs) {
        this.programs = programs;
    }

    @GetMapping
    public List<ProgramDto> list() {
        return programs.list().stream().map(ProgramDto::from).toList();
    }

    @GetMapping("/{id}")
    public ProgramDto get(@PathVariable Long id) {
        return ProgramDto.from(programs.get(id));
    }

    @PatchMapping("/{id}/vetting-settings")
    public ProgramDto updateVettingSettings(@PathVariable Long id, @RequestBody VettingSettingsUpdate update) {
        return ProgramDto.from(programs.updateVettingSettings(id, update));
    }
}
