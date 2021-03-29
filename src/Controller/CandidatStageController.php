<?php

namespace App\Controller;

use App\Entity\CandidatStage;
use App\Form\CandidatStageType;
use App\Repository\CandidatStageRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/candidat/stage")
 */
class CandidatStageController extends AbstractController
{
    /**
     * @Route("/", name="candidat_stage_index", methods={"GET"})
     */
    public function index(CandidatStageRepository $candidatStageRepository): Response
    {
        return $this->render('candidat_stage/index.html.twig', [
            'candidat_stages' => $candidatStageRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="candidat_stage_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $candidatStage = new CandidatStage();
        $form = $this->createForm(CandidatStageType::class, $candidatStage);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($candidatStage);
            $entityManager->flush();

            return $this->redirectToRoute('candidat_stage_index');
        }

        return $this->render('candidat_stage/new.html.twig', [
            'candidat_stage' => $candidatStage,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="candidat_stage_show", methods={"GET"})
     */
    public function show(CandidatStage $candidatStage): Response
    {
        return $this->render('candidat_stage/show.html.twig', [
            'candidat_stage' => $candidatStage,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="candidat_stage_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, CandidatStage $candidatStage): Response
    {
        $form = $this->createForm(CandidatStageType::class, $candidatStage);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('candidat_stage_index');
        }

        return $this->render('candidat_stage/edit.html.twig', [
            'candidat_stage' => $candidatStage,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="candidat_stage_delete", methods={"DELETE"})
     */
    public function delete(Request $request, CandidatStage $candidatStage): Response
    {
        if ($this->isCsrfTokenValid('delete'.$candidatStage->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($candidatStage);
            $entityManager->flush();
        }

        return $this->redirectToRoute('candidat_stage_index');
    }
}
